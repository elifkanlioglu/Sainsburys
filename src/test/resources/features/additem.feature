Feature: The user adds an item to trolley

  @login @ui
  Scenario: Login and add an item to trolley
    Given the user is on the Sainsbury login page
    When the user enters valid credentials
    Then the user should be redirected to the home page
    When the user search "Gravy"
    And adds the first item to the trolley
    Then the user should see the item in the trolley

