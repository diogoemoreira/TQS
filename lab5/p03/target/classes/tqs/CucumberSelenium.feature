Feature: Getting a flight in BlazeDemon

  Scenario: Confirm redirect after choosing a flight
    When I navigate to "https://blazedemo.com/"
    And I choose "Philadelphia" as my departure city
    And I choose "Cairo" as my destination city
    And I click on "Find Flights"
    Then I am redirected to "https://blazedemo.com/reserve.php"
