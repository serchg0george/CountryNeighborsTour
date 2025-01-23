# Country Neighbors Tour

**Country Neighbors Tour** is a RESTful API designed to assist travelers in planning trips to neighboring countries based on a given budget. The API calculates how many times a traveler can visit all neighboring countries, provides the budget required for each country in their respective currencies, and accounts for leftover amounts in the original input currency.

---

## Features

- Calculate the number of complete trips around neighboring countries based on a given budget.
- Determine the budget required for each country in their respective currencies.
- Handle missing exchange rates by providing the budget in the original input currency.
- Return any remaining budget in the original input currency.

---

## Example Use Case

**Input:**
- Starting country: Bulgaria (BG)
- Budget per country: 100
- Total budget: 1200
- Input currency: EUR

**Output:**
- Neighboring countries: Turkey (TR), Greece (GR), North Macedonia (MK), Serbia (SR), Romania (RO)
- Angel can travel around them **2 times**.
- Leftover budget: **200 EUR**.
- Budget breakdown:
  - Turkey: **1325.30 TL**
  - North Macedonia: **12232.51 MKD**
  - (Other countries with respective amounts...)

---

## API Endpoints

### **POST /calculate-trip**
Accepts the following parameters:
- `starting_country` (string): The name of the starting country.
- `budget_per_country` (number): The budget allocated for each country.
- `total_budget` (number): The total budget available for the trip.
- `currency` (string): The input currency code (e.g., EUR, USD).

**Example Request:**
```json
{
  "starting_country": "Bulgaria",
  "budget_per_country": 100,
  "total_budget": 1200,
  "currency": "EUR"
}
```


**Example Response:**

```json
{
  "neighboring_countries": ["Turkey", "Greece", "North Macedonia", "Serbia", "Romania"],
  "number_of_complete_trips": 2,
  "leftover_budget": {
    "amount": 200,
    "currency": "EUR"
  },
  "budgets_per_country": {
    "Turkey": "1325.30 TL",
    "Greece": "100.00 EUR",
    "North Macedonia": "12232.51 MKD",
    "Serbia": "11700.00 RSD",
    "Romania": "490.00 RON"
  }
}
```
