Feature: Login Tests

  Scenario: Login with empty submit
    Given Open login Page
    When Set login fields with login "" and password ""
    Then I verify that "Missing email" message displayed
    And Close browser

  Scenario: User login with valid credentials
    Given Open login Page
    When Set login fields with login "workboris3@gmail.com" and password "B0r1sTr3ll0"
    Then I verify Team board title "Most popular templates"
    And Close browser

  Scenario: User login with invalid credentials - Email
    Given Open login Page
    When Set login fields with login "test@qwdqwdqw" and password ""
    Then I verify that "There isn't an account for this email" message displayed
    And Close browser

  Scenario: Check Login button location
    Given Open login Page
    Then I verify button "login" location - "(296, 332)"
    And Close browser