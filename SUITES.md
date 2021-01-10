Predefined conditions:

1. Backend and Frontend are covered with api tests.
2. Tests should cover the most critical cases of UI and API.
3. API part of APP is more critical and more error-prune.


At which phases of development cycle which tests should be executed? 

Smoke tests that covers the most critical parts might be embedded in a development pipeline and executed each time after merging in master branch. 


## Test cases UI


**Account Page**

```
SCENARIO 1. User is able to create account
WHEN User A wants to create account with valid data
THEN Account has been created
AND User can see success message
```

```
SCENARIO 2. User is not able to create account with empty balance
WHEN User A wants to create account with empty balance
THEN Account has not been created
AND User can see error message
```

**Account List Page**

```
SCENARIO 1. A new account appears in the account list with correct data
GIVEN User creates new account
WHEN User navigates to Account List page
THEN User should see the account
AND Account data is valid
```

**Transaction Page**

```
SCENARIO 1. A user can create a transaction
GIVEN User creates new transaction
WHEN Transaction data is valid
THEN Transaction has been created
AND User can see a success message
```

```
SCENARIO 2. User sees an error message if the transfer amount exceeds the balance
GIVEN User creates new transaction 
WHEN Transaction amount exceeds the balance
THEN Transaction has not been created
AND User can see an error message
```

```
SCENARIO 3. User sees an error message if a source account is equal to a destination account
GIVEN User creates new transaction 
WHEN User selects a destination account equals to a source account
THEN Transaction has not been created
AND User can see an error message
```

**Transaction List Page**

```
SCENARIO 1. Transaction list page with default parameters displays transactions
GIVEN Transaction for today is created
WHEN User navigates to Transaction List page
THEN User should see transactions for today
```

```
SCENARIO 2. New transactions appears in the transaction list with correct data
GIVEN Transaction for today is created
WHEN User navigates to Transaction List page
AND User set from = today and to = tomorrow
THEN User should see the transaction
AND Transaction data is valid
```

## Test cases API

**Account API Test**

```
SCENARIO 1. Verify that a user can create an account with valid data
WHEN User creates an account with valid data
THEN Account has been created
```

```
SCENARIO 2. Verify that a user can not create an account with negative balance
GIVEN User creates an account
WHEN Balance is negative
THEN Account has not been created
```

```
SCENARIO 3. Verify that a user can not create an account with empty name
GIVEN User creates an account
WHEN Owner name is empty string
THEN Account has not been created
```

```
SCENARIO 4. Verify that a user can get account details by id
GIVEN User creates an account
WHEN User request account details by id
THEN Response contains valid account data
```

```
SCENARIO 5. Verify account list api
GIVEN User creates an account
WHEN User request all exsisting accounts
THEN Response contains accounts
```

**Transaction API Test**

```
SCENARIO 1. Transfer money from one account to another with different currencies
GIVEN User creates a transaction with valid data
WHEN Source account and destination account have differnet currencies
THEN Transaction has been created
```


```
SCENARIO 2. Transfer money from one account to another with the same currency
GIVEN User creates a transaction with valid data
WHEN Source account and destination account have the same currencies
THEN Transaction has been created
```

```
SCENARIO 3. Transfer an amount that exceeded the account balance
GIVEN User creates a transaction
WHEN An amount exceeds the source account balance
THEN Transaction has not been created
AND Response contains an error message
```

```
SCENARIO 4. Transfer negative value
GIVEN User creates a transaction
WHEN An amount is negative value
THEN Transaction has not been created
AND Response contains an error message
```

```
SCENARIO 5. Transfer zero value
GIVEN User creates a transaction
WHEN An amount is equal to zero
THEN Transaction has not been created
AND Response contains an error message
```

```
SCENARIO 6. Verify transaction list API
GIVEN User creates an transaction
WHEN User request all exsisting transaction from todat to tomorrow
THEN Response contains the transaction
AND Transaction data is valid
```
