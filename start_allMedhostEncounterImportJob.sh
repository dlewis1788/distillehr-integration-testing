#!/usr/bin/env ksh
typeset -x _HOSTNAME_PORT
typeset -x _USER
typeset -x _PASSWORD
typeset -x _JOB_EXECUTION_JSON
typeset -x _JOB_INSTANCE_ID
typeset -x _JOB_EXECUTION_STATUS
typeset -x _JOB_LAUNCHER_QUEUE_SIZE_JSON
typeset -i _JOB_LAUNCHER_QUEUE_SIZE

_HOSTNAME_PORT=${1}
_USER=admin
_PASSWORD=secret
_JOB_EXECUTION_JSON=$(curl -s "http://${_USER}:${_PASSWORD}@${_HOSTNAME_PORT}/batch/allMedhostEncounterImportJob/start")
_JOB_INSTANCE_ID=$(echo "${_JOB_EXECUTION_JSON}" | jq -r ".id")
_JOB_EXECUTION_STATUS=$(echo "${_JOB_EXECUTION_JSON}" | jq -r ".status")

while true
do
	_JOB_EXECUTION_STATUS=$(curl -s "http://${_USER}:${_PASSWORD}@${_HOSTNAME_PORT}/batch/jobExplorer/getJobExecutionsByJobInstanceId/${_JOB_INSTANCE_ID}" | jq -r ".[0].status")

	if [ "${_JOB_EXECUTION_STATUS}" = "STARTING" ]
	then
		echo "allMedhostEncounterImportJob starting..."
		sleep 5
	else
		break
	fi
done

if [ "${_JOB_EXECUTION_STATUS}" != "STARTED" ]
then
	echo "Job allMedhostEncounterImportJob unsuccessfully started. Job status is ${_JOB_EXECUTION_STATUS}." 
	exit 1
else
	echo "Job allMedhostEncounterImportJob successfully started."
fi

while true
do
	_JOB_EXECUTION_STATUS=$(curl -s "http://${_USER}:${_PASSWORD}@${_HOSTNAME_PORT}/batch/jobExplorer/getJobExecutionsByJobInstanceId/${_JOB_INSTANCE_ID}" | jq -r ".[0].status")
	
	if [ "${_JOB_EXECUTION_STATUS}" = "STARTED" ]
	then
		echo "Job allMedhostEncounterImportJob still running."
		sleep 5
		continue
	elif [ "${_JOB_EXECUTION_STATUS}" = "COMPLETED" ]
	then
		echo "Job allMedhostEncounterImportJob completed."
		break	
	else
		echo "Unknown job status (${_JOB_EXECUTION_STATUS}) for job allMedhostEncounterImportJob. Exiting."
		exit 1
	fi
done

_JOB_LAUNCHER_QUEUE_SIZE_JSON=$(curl -s "http://${_USER}:${_PASSWORD}@${_HOSTNAME_PORT}/actuator/jolokia/read/org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Queue,destinationName=jobLauncherQueue/QueueSize")
_JOB_LAUNCHER_QUEUE_SIZE=$(echo "${_JOB_LAUNCHER_QUEUE_SIZE_JSON}" | jq -r ".value")

if [ ${_JOB_LAUNCHER_QUEUE_SIZE} -eq 0 ]
then
	echo "jobLauncherQueue QueueSize should not be 0. Exiting."
	exit 1
fi

while true
do
	_JOB_LAUNCHER_QUEUE_SIZE_JSON=$(curl -s "http://${_USER}:${_PASSWORD}@${_HOSTNAME_PORT}/actuator/jolokia/read/org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Queue,destinationName=jobLauncherQueue/QueueSize")
	_JOB_LAUNCHER_QUEUE_SIZE=$(echo "${_JOB_LAUNCHER_QUEUE_SIZE_JSON}" | jq -r ".value")
	
	if [ ${_JOB_LAUNCHER_QUEUE_SIZE} -gt 0 ]
	then
		echo "jobLauncherQueue QueueSize greater than 0. QueueSize = ${_JOB_LAUNCHER_QUEUE_SIZE}. Continuing..."
		sleep 60
	else
		echo "jobLauncherQueue empty. All child jobs executed."
		exit 0
	fi
done
