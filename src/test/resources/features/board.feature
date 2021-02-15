Feature: Board Tests

  Scenario: Check placeholder text in the search field
    Given Open login Page
    When Set login fields with login "workboris4@gmail.com" and password "B0r1sTr3ll0"
    And Open Boards Menu
    Then Expected "placeholder" must be "Find boards by name…". If failed - write text "Text is wrong"
    And Close browser

  Scenario: Check title for existed board
    Given Open login Page
    When Set login fields with login "workboris4@gmail.com" and password "B0r1sTr3ll0"
    And Open Boards Menu
    Then Expected the board title
    And The board title is "Borys Trello 1"

  Scenario: Add new board check with deleting at the end
    Given Open login Page
    When Set login fields with login "workboris4@gmail.com" and password "B0r1sTr3ll0"
    And Add new board with title "Test Board Title"
    Then The board title is "Test Board Title"
    And Close board "Test Board Title"