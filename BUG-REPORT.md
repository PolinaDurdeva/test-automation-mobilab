## Bug report 

**Summary:** Transaction with negative balance can be created

**Priority:** critical
 
**App:** Backend

**Environment:** dev

**URL:** http://lockalhost:8080

**Steps to reproduce:**

Make a request `POST /transactions` to create new transaction with negative `amount`

**Expected result:**

Response code: 400 Bad Request

Message: some clear message

**Actual result:**

Response code: 200

Transaction with a negative amount is created