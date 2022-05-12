#!/usr/bin/env ksh
typeset -x _HOSTNAME_PORT
typeset -x _USER
typeset -x _PASSWORD
typeset -x _JOB_EXECUTION_JSON
typeset -x _JOB_INSTANCE_ID
typeset -x _JOB_EXECUTION_STATUS

_HOSTNAME_PORT=${1}
_USER=admin
_PASSWORD=secret
_JOB_EXECUTION_JSON=$(curl -s "http://${_USER}:${_PASSWORD}@${_HOSTNAME_PORT}/batch/mpiUploadJob/start")
_JOB_INSTANCE_ID=$(echo "${_JOB_EXECUTION_JSON}" | jq -r ".id")
_JOB_EXECUTION_STATUS=$(echo "${_JOB_EXECUTION_JSON}" | jq -r ".status")

while true
do
	_JOB_EXECUTION_STATUS=$(curl -s "http://${_USER}:${_PASSWORD}@${_HOSTNAME_PORT}/batch/jobExplorer/getJobExecutionsByJobInstanceId/${_JOB_INSTANCE_ID}" | jq -r ".[0].status")

	if [ "${_JOB_EXECUTION_STATUS}" = "STARTING" ]
	then
		echo "mpiUploadJob starting..."
		sleep 5
	else
		break
	fi
done

if [ "${_JOB_EXECUTION_STATUS}" != "STARTED" ]
then
	echo "Job mpiUploadJob unsuccessfully started. Job status is ${_JOB_EXECUTION_STATUS}." 
	exit 1
else
	echo "Job mpiUploadJob successfully started."
fi

while true
do
	_JOB_EXECUTION_STATUS=$(curl -s "http://${_USER}:${_PASSWORD}@${_HOSTNAME_PORT}/batch/jobExplorer/getJobExecutionsByJobInstanceId/${_JOB_INSTANCE_ID}" | jq -r ".[0].status")
	
	if [ "${_JOB_EXECUTION_STATUS}" = "STARTED" ]
	then
		echo "Job mpiUploadJob still running."
		sleep 5
		continue
	elif [ "${_JOB_EXECUTION_STATUS}" = "COMPLETED" ]
	then
		echo "Job mpiUploadJob completed."
		exit 0
	else
		echo "Unknown job status (${_JOB_EXECUTION_STATUS}) for job mpiUploadJob. Exiting."
		exit 1
	fi
done
