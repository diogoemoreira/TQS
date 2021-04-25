Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  Background:
    Given a book with the title 'Percy Jackson: The Lightning Thief', written by 'Rick Riordan', published in 2005-06-28
    And another book with the title 'Percy Jackson: The Last Olimpian', written by 'Rick Riordan', published in 2009-05-05
    And another book with the title 'Harry Potter and the Philosopher\'s Stone', written by 'J K Rowling', published in 1997-06-26
    And another book with the title 'Artemis Fowl: The Lost Colony', written by 'Eoin Colfer', published in 2006-08-02
    And another book with the title 'Percy Jackson: The Lost Hero', written by 'Rick Riordan', published in 2010-10-12
    And another book with the title 'Percy Jackson: The Sea of Monsters', written by 'Rick Riordan', published in 2006-04-01

  Scenario: Search books by publication year
    When the customer searches for books published between 2005-01-01 and 2009-12-31
    Then 4 books should have been found
    And Book 1 should have the title 'Percy Jackson: The Last Olimpian'
    And Book 2 should have the title 'Artemis Fowl: The Lost Colony'
    And Book 3 should have the title 'Percy Jackson: The Sea of Monsters'
    And Book 4 should have the title 'Percy Jackson: The Lightning Thief'

  Scenario: Search books by Author
    When the customer searches for a book with the author 'Rick Riordan'
    Then 4 books should have been found
    And Book 1 should have the title 'Percy Jackson: The Lightning Thief'
    And Book 2 should have the title 'Percy Jackson: The Last Olimpian'
    And Book 3 should have the title 'Percy Jackson: The Lost Hero'
    And Book 4 should have the title 'Percy Jackson: The Sea of Monsters'

  Scenario: Search books by Title
    When the customer searches for a book with the title 'Percy Jackson'
    Then 4 books should have been found
    And Book 1 should have the title 'Percy Jackson: The Lightning Thief'
    And Book 2 should have the title 'Percy Jackson: The Last Olimpian'
    And Book 3 should have the title 'Percy Jackson: The Lost Hero'
    And Book 4 should have the title 'Percy Jackson: The Sea of Monsters'
