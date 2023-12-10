# @author abifadila on 08/12/2023
# @project Challenge Chapter 7 - Web Automation
# @for SYNRGY Academy Batch VI x BCA Group

@All
Feature: Product Checkout
  As a user, I want to be able to log in to the website, find a product, and proceed through the checkout flow.

  @Test @Checkout
  Scenario: Successful checkout
    Given I am on the products page
    When I click add to cart button on products
    And I click the shopping cart icon
    Then I should be redirected to the saucedemo.com cart page
    When I click the checkout button
    And I enter my personal information
    And I click the continue button
    And I confirm my order
    Then I should see the confirmation page
