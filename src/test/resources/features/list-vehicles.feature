Feature: List Available Vehicles
  As a customer/sales partner
  I want to view a list of available/sold vehicles ordered by price
  So that I can make informed decisions about purchasing or selling vehicles

  Scenario: Successfully list available vehicles ordered by price
    Given the system has multiple vehicles available for order
    When the customer requests the list of available vehicles
    Then the system should return a list of all available vehicles ordered by price in ascending order

  Scenario: No vehicles available for order
    Given the system has no vehicles available for order
    When the customer requests the list of available vehicles
    Then the system should return an empty available vehicles list

  Scenario: Successfully list all sold vehicles
    Given the system has multiple sold vehicles
    When the partner requests to list all sold vehicles
    Then the system should return a list of all sold vehicles ordered by price in ascending order


  Scenario: No sold vehicles
    Given the system has no sold vehicles
    When the partner requests to list all sold vehicles
    Then the system should return an empty sold vehicles list
