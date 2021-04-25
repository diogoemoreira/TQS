Feature: Basic Arithmetic
  Background: A Calculator
    Given a turned on calculator
  Scenario: Addition
    When I add 2 and 1
    Then the result is 3
  Scenario: Substraction
    When I substract 5 to 3
    Then the result is 2
  Scenario Outline: Several additions
    When I add <a> and <b>
    Then the result is <c>
    Examples: Single digits
      | a | b | c  |
      | 1 | 2 | 3  |
      | 3 | 8 | 11 |