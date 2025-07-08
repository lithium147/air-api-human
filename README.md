ShopItemCategory make more sense to be the join entity between ShopItem and Category

# Also add endpoint autotests for each endpoint

What is the autotests endpoint meant to do?

Ah, maybe this is just saying to add endpoint tests
Add the wiremock style tests

- need the wiremock base class - no, thats for testing clients

The generated tests are end to end, would be better to be controller layer only.

|                  | CRUD | Tests |   |   |
|------------------|------|-------|---|---|
| Customer         |      |       |   |   |
| ShopItemCategory |      |       |   |   |
| ShopItem         | CR-D | CR-D  |   |   |
| Order            |      |       |   |   |

Finish one end point completely before doing others




