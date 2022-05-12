@GrabConfig(systemClassLoader=true)
@Grab('org.postgresql:postgresql:9.4.1208')
import groovy.sql.Sql
@Grab('org.apache.commons:commons-csv:1.4')
import org.apache.commons.csv.CSVPrinter
import org.apache.commons.csv.CSVFormat

def hostname_port = args[0]
def username = 'distillehr'
def password = 'foobar11'
def filename = 'checkErrors_allMedhostEncounterImportJob.err.txt'

def db = Sql.newInstance('jdbc:postgresql://' + hostname_port + '/distillehr-medhost-enterprise-extraction-service-db',
        username,
        password,
        'org.postgresql.Driver')
def checkErrorCountSql = "select count(*) from batch_job_execution e " +
        "join batch_job_execution_params p on e.job_execution_id = p.job_execution_id " +
        "where e.status != 'COMPLETED'"

def checkErrorCountResult = db.firstRow(checkErrorCountSql)
long errorCount = checkErrorCountResult.count

println("Error count: " + errorCount)

if (errorCount != 446L) {
    println("Error count mismatch. Writing batch_job_execution to CSV file.")
    def csvFormat = CSVFormat.DEFAULT.withDelimiter("|".toCharacter()).withRecordSeparator("\r\n")
    def fileWriter = new FileWriter(filename)
    def csvPrinter = new CSVPrinter(fileWriter, csvFormat)
    def errorSql = "select * from batch_job_execution e " +
            "join batch_job_execution_params p on e.job_execution_id = p.job_execution_id " +
            "where e.status = 'FAILED'"
    db.rows(errorSql).each {
        row ->
            csvPrinter.printRecord(row.collect {
                entry -> entry.value
            })
    }
    System.exit(1)
} else {
    System.exit(0)
}
