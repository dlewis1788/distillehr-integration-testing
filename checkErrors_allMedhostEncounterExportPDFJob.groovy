@GrabConfig(systemClassLoader=true)
@Grab('org.postgresql:postgresql:9.4.1208')
import groovy.sql.Sql

def hostname_port = args[0]
def username = 'distillehr'
def password = 'foobar11'

def db = Sql.newInstance('jdbc:postgresql://' + hostname_port + '/distillehr-medhost-enterprise-extraction-service-db',
        username,
        password,
        'org.postgresql.Driver')

def checkErrorCountSql = "select count(*) from batch_job_execution e " +
        "join batch_job_execution_params p on e.job_execution_id = p.job_execution_id " +
        "where e.status = 'FAILED'"

def checkErrorCountResult = db.firstRow(checkErrorCountSql)
long errorCount = checkErrorCountResult.count

println("Error count: " + errorCount)

if (errorCount != 446L)
    System.exit(1)

def checkEncounterPdfExportCountSql = "select count(*) from encounter_pdf_export"

def checkEncounterPdfExportCountResult = db.firstRow(checkEncounterPdfExportCountSql)
long encounterPdfExportCount = checkEncounterPdfExportCountResult.count

println("EncounterPDFExport count: " + encounterPdfExportCount)

if (encounterPdfExportCount != 22449L)
    System.exit(1)
