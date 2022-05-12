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

println("Verifying record counts all encounters.\n")

//
// Verify MedhostAllergy record count
//

def checkAllEncounterMedhostAllergyCountSql = "select count(*) from medhost_allergy"
def checkAllEncounterMedhostAllergyCountResult = db.firstRow(checkAllEncounterMedhostAllergyCountSql)
long allEncounterMedhostAllergyCount = checkAllEncounterMedhostAllergyCountResult.count
println("MedhostAllergy records: ".padRight(70) + allEncounterMedhostAllergyCount)

if (allEncounterMedhostAllergyCount != 6924L) {
    println("MedhostAllergy record mismatch.")
    System.exit(1)
}

//
// Verify MedhostNote record count
//

def checkAllEncounterMedhostNoteCountSql = "select count(*) from medhost_note"
def checkAllEncounterMedhostNoteCountResult = db.firstRow(checkAllEncounterMedhostNoteCountSql)
long allEncounterMedhostNoteCount = checkAllEncounterMedhostNoteCountResult.count
println("MedhostNote records: ".padRight(70) + allEncounterMedhostNoteCount)

if (allEncounterMedhostNoteCount != 5250L) {
    println("MedhostNote record mismatch.")
    System.exit(1)
}

//
// Verify MedhostPatientProblem record count
//

def checkAllEncounterMedhostPatientProblemCountSql = "select count(*) from medhost_patient_problem"
def checkAllEncounterMedhostPatientProblemCountResult = db.firstRow(checkAllEncounterMedhostPatientProblemCountSql)
long allEncounterMedhostPatientProblemCount = checkAllEncounterMedhostPatientProblemCountResult.count
println("MedhostPatientProblem records: ".padRight(70) + allEncounterMedhostPatientProblemCount)

if (allEncounterMedhostPatientProblemCount != 2708L) {
    println("MedhostPatientProblem record mismatch.")
    System.exit(1)
}

//
// Verify MedhostVitals record count
//

def checkAllEncounterMedhostVitalsCountSql = "select count(*) from medhost_vitals"
def checkAllEncounterMedhostVitalsCountResult = db.firstRow(checkAllEncounterMedhostVitalsCountSql)
long allEncounterMedhostVitalsCount = checkAllEncounterMedhostVitalsCountResult.count
println("MedhostVitals records: ".padRight(70) + allEncounterMedhostVitalsCount)

if (allEncounterMedhostVitalsCount != 2944L) {
    println("MedhostVitals record mismatch.")
    System.exit(1)
}

//
// Verify MedhostOrder record count
//

def checkAllEncounterMedhostOrderCountSql = "select count(*) from medhost_orders"
def checkAllEncounterMedhostOrderCountResult = db.firstRow(checkAllEncounterMedhostOrderCountSql)
long allEncounterMedhostOrderCount = checkAllEncounterMedhostOrderCountResult.count
println("MedhostOrder records: ".padRight(70) + allEncounterMedhostOrderCount)

if (allEncounterMedhostOrderCount != 11458L) {
    println("MedhostOrder record mismatch.")
    System.exit(1)
}

//
// Verify MedhostOrderResult record count
//

def checkAllEncounterMedhostOrderResultCountSql = "select count(*) from medhost_order_results"
def checkAllEncounterMedhostOrderResultCountResult = db.firstRow(checkAllEncounterMedhostOrderResultCountSql)
long allEncounterMedhostOrderResultCount = checkAllEncounterMedhostOrderResultCountResult.count
println("MedhostOrderResult records: ".padRight(70) + allEncounterMedhostOrderResultCount)

if (allEncounterMedhostOrderResultCount != 36083L) {
    println("MedhostOrderResult record mismatch.")
    System.exit(1)
}

//
// Verify MedhostOrderAmbulatoryStatus record count
//

def checkAllEncounterMedhostOrderAmbulatoryStatusCountSql = "select count(*) from medhost_order_ambulatory_status"
def checkAllEncounterMedhostOrderAmbulatoryStatusCountResult = db.firstRow(checkAllEncounterMedhostOrderAmbulatoryStatusCountSql)
long allEncounterMedhostOrderAmbulatoryStatusCount = checkAllEncounterMedhostOrderAmbulatoryStatusCountResult.count
println("MedhostOrderAmbulatoryStatus records: ".padRight(70) + allEncounterMedhostOrderAmbulatoryStatusCount)

if (allEncounterMedhostOrderAmbulatoryStatusCount != 0L) {
    println("MedhostOrderAmbulatoryStatus record mismatch.")
    System.exit(1)
}

//
// Verify MedhostOrderRadiologyMessage record count
//

def checkAllEncounterMedhostOrderRadiologyMessageCountSql = "select count(*) from medhost_order_radiology_messages"
def checkAllEncounterMedhostOrderRadiologyMessageCountResult = db.firstRow(checkAllEncounterMedhostOrderRadiologyMessageCountSql)
long allEncounterMedhostOrderRadiologyMessageCount = checkAllEncounterMedhostOrderRadiologyMessageCountResult.count
println("MedhostOrderRadiologyMessageStatus records: ".padRight(70) + allEncounterMedhostOrderRadiologyMessageCount)

if (allEncounterMedhostOrderRadiologyMessageCount != 7950L) {
    println("MedhostOrderRadiologyMessage record mismatch.")
    System.exit(1)
}

//
// Verify MedhostInpatientMedication record count
//

def checkAllEncounterMedhostInpatientMedicationCountSql = "select count(*) from medhost_inpatient_medication"
def checkAllEncounterMedhostInpatientMedicationCountResult = db.firstRow(checkAllEncounterMedhostInpatientMedicationCountSql)
long allEncounterMedhostInpatientMedicationCount = checkAllEncounterMedhostInpatientMedicationCountResult.count
println("MedhostInpatientMedication records: ".padRight(70) + allEncounterMedhostInpatientMedicationCount)

if (allEncounterMedhostInpatientMedicationCount != 34236L) {
    println("MedhostInpatientMedication record mismatch.")
    System.exit(1)
}

//
// Verify MedhostCareStandardOverride record count
//

def checkAllEncounterMedhostCareStandardOverrideCountSql = "select count(*) from medhost_care_standard_override"
def checkAllEncounterMedhostCareStandardOverrideCountResult = db.firstRow(checkAllEncounterMedhostCareStandardOverrideCountSql)
long allEncounterMedhostCareStandardOverrideCount = checkAllEncounterMedhostCareStandardOverrideCountResult.count
println("MedhostCareStandardOverride records: ".padRight(70) + allEncounterMedhostCareStandardOverrideCount)

if (allEncounterMedhostCareStandardOverrideCount != 2586L) {
    println("MedhostCareStandardOverride record mismatch.")
    System.exit(1)
}

//
// Verify MedhostMedicationReconciliationEvent record count
//

def checkAllEncounterMedhostMedicationReconciliationEventCountSql = "select count(*) from medhost_medication_reconciliation_event"
def checkAllEncounterMedhostMedicationReconciliationEventCountResult = db.firstRow(checkAllEncounterMedhostMedicationReconciliationEventCountSql)
long allEncounterMedhostMedicationReconciliationEventCount = checkAllEncounterMedhostMedicationReconciliationEventCountResult.count
println("MedhostMedicationReconciliationEvent records: ".padRight(70) + allEncounterMedhostMedicationReconciliationEventCount)

if (allEncounterMedhostMedicationReconciliationEventCount != 8907L) {
    println("MedhostMedicationReconciliationEvent record mismatch.")
    System.exit(1)
}

//
// Verify MedhostMedicationAdministeredEvent record count
//

def checkAllEncounterMedhostMedicationAdministeredEventCountSql = "select count(*) from medhost_medication_administered_event"
def checkAllEncounterMedhostMedicationAdministeredEventCountResult = db.firstRow(checkAllEncounterMedhostMedicationAdministeredEventCountSql)
long allEncounterMedhostMedicationAdministeredEventCount = checkAllEncounterMedhostMedicationAdministeredEventCountResult.count
println("MedhostMedicationAdministeredEvent records: ".padRight(70) + allEncounterMedhostMedicationAdministeredEventCount)

if (allEncounterMedhostMedicationAdministeredEventCount != 5288L) {
    println("MedhostMedicationAdministeredEvent record mismatch.")
    System.exit(1)
}

println("\nPASSED.")

// Verify record counts for encounterId 4000422

long encounterId = 4000422

println("\nVerifying record counts for encounterId " + encounterId + ".\n")

def checkSingleEncounterMedhostAllergyCountSql = "select count(*) from medhost_allergy where medhost_encounter_id = ?"
def checkSingleEncounterMedhostAllergyCountResult = db.firstRow(checkSingleEncounterMedhostAllergyCountSql, [encounterId])
long singleEncounterMedhostAllergyCount = checkSingleEncounterMedhostAllergyCountResult.count
println("MedhostAllergy records: ".padRight(70) + singleEncounterMedhostAllergyCount)

if (singleEncounterMedhostAllergyCount != 2L) {
    println("encounterId " + encounterId + " MedhostAllergy record mismatch.")
    System.exit(1)
}

def checkSingleEncounterMedhostNoteCountSql = "select count(*) from medhost_note where medhost_encounter_id = ?"
def checkSingleEncounterMedhostNoteCountResult = db.firstRow(checkSingleEncounterMedhostNoteCountSql, [encounterId])
long singleEncounterMedhostNoteCount = checkSingleEncounterMedhostNoteCountResult.count
println("MedhostNote records: ".padRight(70) + singleEncounterMedhostNoteCount)

if (singleEncounterMedhostNoteCount != 83L) {
    println("encounterId " + encounterId + " MedhostNote record mismatch.")
    System.exit(1)
}

def checkSingleEncounterMedhostPatientProblemCountSql = "select count(*) from medhost_patient_problem where medhost_encounter_id = ?"
def checkSingleEncounterMedhostPatientProblemCountResult = db.firstRow(checkSingleEncounterMedhostPatientProblemCountSql, [encounterId])
long singleEncounterMedhostPatientProblemCount = checkSingleEncounterMedhostPatientProblemCountResult.count
println("MedhostPatientProblem records: ".padRight(70) + singleEncounterMedhostPatientProblemCount)

if (singleEncounterMedhostPatientProblemCount != 5L) {
    println("encounterId " + encounterId + " MedhostPatientProblem record mismatch.")
    System.exit(1)
}

def checkSingleEncounterMedhostVitalsCountSql = "select count(*) from medhost_vitals where medhost_encounter_id = ?"
def checkSingleEncounterMedhostVitalsCountResult = db.firstRow(checkSingleEncounterMedhostVitalsCountSql, [encounterId])
long singleEncounterMedhostVitalsCount = checkSingleEncounterMedhostVitalsCountResult.count
println("MedhostVitals records: ".padRight(70) + singleEncounterMedhostVitalsCount)

if (singleEncounterMedhostVitalsCount != 5L) {
    println("encounterId " + encounterId + " MedhostVitals record mismatch.")
    System.exit(1)
}

def checkSingleEncounterMedhostOrdersCountSql = "select count(*) from medhost_orders where medhost_encounter_id = ?"
def checkSingleEncounterMedhostOrdersCountResult = db.firstRow(checkSingleEncounterMedhostOrdersCountSql, [encounterId])
long singleEncounterMedhostOrdersCount = checkSingleEncounterMedhostOrdersCountResult.count
println("MedhostOrders records: ".padRight(70) + singleEncounterMedhostOrdersCount)

if (singleEncounterMedhostOrdersCount != 14L) {
    println("encounterId " + encounterId + " MedhostOrders record mismatch.")
    System.exit(1)
}

def checkSingleEncounterMedhostOrderResultCountSql = "select count(*) from medhost_order_results where medhost_encounter_id = ?"
def checkSingleEncounterMedhostOrderResultCountResult = db.firstRow(checkSingleEncounterMedhostOrderResultCountSql, [encounterId])
long singleEncounterMedhostOrderResultCount = checkSingleEncounterMedhostOrderResultCountResult.count
println("MedhostOrderResult records: ".padRight(70) + singleEncounterMedhostOrderResultCount)

if (singleEncounterMedhostOrderResultCount != 58L) {
    println("encounterId " + encounterId + " MedhostOrderResult record mismatch.")
    System.exit(1)
}

def checkSingleEncounterMedhostOrderAmbulatoryStatusCountSql = "select count(*) from medhost_order_ambulatory_status where medhost_encounter_id = ?"
def checkSingleEncounterMedhostOrderAmbulatoryStatusCountResult = db.firstRow(checkSingleEncounterMedhostOrderAmbulatoryStatusCountSql, [encounterId])
long singleEncounterMedhostOrderAmbulatoryStatusCount = checkSingleEncounterMedhostOrderAmbulatoryStatusCountResult.count
println("MedhostOrderAmbulatoryStatus records: ".padRight(70) + singleEncounterMedhostOrderAmbulatoryStatusCount)

if (singleEncounterMedhostOrderAmbulatoryStatusCount != 0L) {
    println("encounterId " + encounterId + " MedhostOrderAmbulatoryStatus record mismatch.")
    System.exit(1)
}

def checkSingleEncounterMedhostOrderRadiologyMessageCountSql = "select count(*) from medhost_order_radiology_messages where medhost_encounter_id = ?"
def checkSingleEncounterMedhostOrderRadiologyMessageCountResult = db.firstRow(checkSingleEncounterMedhostOrderRadiologyMessageCountSql, [encounterId])
long singleEncounterMedhostOrderRadiologyMessageCount = checkSingleEncounterMedhostOrderRadiologyMessageCountResult.count
println("MedhostOrderRadiologyMessage records: ".padRight(70) + singleEncounterMedhostOrderRadiologyMessageCount)

if (singleEncounterMedhostOrderRadiologyMessageCount != 22L) {
    println("encounterId " + encounterId + " MedhostOrderRadiologyMessage record mismatch.")
    System.exit(1)
}

def checkSingleEncounterMedhostInpatientMedicationCountSql = "select count(*) from medhost_inpatient_medication where medhost_encounter_id = ?"
def checkSingleEncounterMedhostInpatientMedicationCountResult = db.firstRow(checkSingleEncounterMedhostInpatientMedicationCountSql, [encounterId])
long singleEncounterMedhostInpatientMedicationCount = checkSingleEncounterMedhostInpatientMedicationCountResult.count
println("MedhostInpatientMedication records: ".padRight(70) + singleEncounterMedhostInpatientMedicationCount)

if (singleEncounterMedhostInpatientMedicationCount != 65L) {
    println("encounterId " + encounterId + " MedhostInpatientMedication record mismatch.")
    System.exit(1)
}

def checkSingleEncounterMedhostCareStandardOverrideCountSql = "select count(*) from medhost_care_standard_override where medhost_encounter_id = ?"
def checkSingleEncounterMedhostCareStandardOverrideCountResult = db.firstRow(checkSingleEncounterMedhostCareStandardOverrideCountSql, [encounterId])
long singleEncounterMedhostCareStandardOverrideCount = checkSingleEncounterMedhostCareStandardOverrideCountResult.count
println("MedhostCareStandardOverride records: ".padRight(70) + singleEncounterMedhostCareStandardOverrideCount)

if (singleEncounterMedhostCareStandardOverrideCount != 14L) {
    println("encounterId " + encounterId + " MedhostCareStandardOverride record mismatch.")
    System.exit(1)
}

def checkSingleEncounterMedhostMedicationReconciliationEventCountSql = "select count(*) from medhost_medication_reconciliation_event where medhost_encounter_id = ?"
def checkSingleEncounterMedhostMedicationReconciliationEventCountResult = db.firstRow(checkSingleEncounterMedhostMedicationReconciliationEventCountSql, [encounterId])
long singleEncounterMedhostMedicationReconciliationEventCount = checkSingleEncounterMedhostMedicationReconciliationEventCountResult.count
println("MedhostMedicationReconciliationEvent records: ".padRight(70) + singleEncounterMedhostMedicationReconciliationEventCount)

if (singleEncounterMedhostMedicationReconciliationEventCount != 27L) {
    println("encounterId " + encounterId + " MedhostMedicationReconciliationEvent record mismatch.")
    System.exit(1)
}

def checkSingleEncounterMedhostMedicationAdministeredEventCountSql = "select count(*) from medhost_medication_administered_event where medhost_encounter_id = ?"
def checkSingleEncounterMedhostMedicationAdministeredEventCountResult = db.firstRow(checkSingleEncounterMedhostMedicationAdministeredEventCountSql, [encounterId])
long singleEncounterMedhostMedicationAdministeredEventCount = checkSingleEncounterMedhostMedicationAdministeredEventCountResult.count
println("MedhostMedicationAdministeredEvent records: ".padRight(70) + singleEncounterMedhostMedicationAdministeredEventCount)

if (singleEncounterMedhostMedicationAdministeredEventCount != 41L) {
    println("encounterId " + encounterId + " MedhostMedicationAdministeredEvent record mismatch.")
    System.exit(1)
}

println("\nPASSED.\n")
