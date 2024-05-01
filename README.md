## INSTRUCTION

### Running the App

1. Open your IDE (e.g., IntelliJ IDEA).
2. Run the app using the green run button (no additional configuration needed).
3. If anything doesn't work, try running `mvn clean install` in the terminal, then rerun the app.
4. The app runs on port 8080, so the base URL is: [http://localhost:8080/](http://localhost:8080/)

**Note:** The application comes with pre-populated data. You can check these data in the H2 database console or in the `data.sql` file located in the project's resources directory.

### Endpoints Descriptions with Sample Request Bodies

#### 1. Create a New Product

- **Method:** `POST`
- **URL:** [http://localhost:8080/product](http://localhost:8080/product)
- **Request Body:**

    ```json
    {
        "name": "Example Product",
        "description": "This is an example product description.",
        "regularPrice": 25.99,
        "currency": "USD"
    }
    ```

#### 2. Get All Products

- **Method:** `GET`
- **URL:** [http://localhost:8080/product](http://localhost:8080/product)

#### 3. Update Product Data

- **Method:** `PUT`
- **URL:** [http://localhost:8080/product](http://localhost:8080/product)
- **Request Body:**

    ```json
    {
        "id": 1,
        "name": "HAVE A NICE DAY",
        "description": "This is an example product description.",
        "regularPrice": 25.99,
        "currency": "USD"
    }
    ```

#### 4. Create a New Promo Code - Amount-Based

- **Method:** `POST`
- **URL:** [http://localhost:8080/promocode/amount](http://localhost:8080/promocode/amount)
- **Request Body:**

    ```json
    {
        "code": "SAVE150",
        "expirationDate": "2024-12-31T00:00:00",
        "currency": "USD",
        "maxUsages": 100,
        "discountAmount": 150.0
    }
    ```

#### 4.1. Create a New Promo Code - Percentage-Based

- **Method:** `POST`
- **URL:** [http://localhost:8080/promocode/percentage](http://localhost:8080/promocode/percentage)
- **Request Body:**

    ```json
    {
        "code": "SAVE19",
        "expirationDate": "2024-12-31T00:00:00",
        "currency": "USD",
        "maxUsages": 100,
        "discountRate": 0.19
    }
    ```

#### 5. Get All Promo Codes

- **Method:** `GET`
- **URL:** [http://localhost:8080/promocode/all](http://localhost:8080/promocode/all)

#### 6. Get Promo Code Details by Code

- **Method:** `GET`
- **URL:** [http://localhost:8080/promocode/{code}](http://localhost:8080/promocode/{code})
- **Example URL:** [http://localhost:8080/promocode/WINTER2023](http://localhost:8080/promocode/WINTER2023)

#### 7. Get the Discount Price

- **Method:** `POST`
- **URL:** [http://localhost:8080/discount](http://localhost:8080/discount)
- **Request Body:**

    ```json
    {
        "productId": 1,
        "promoCode": "SAVE50"
    }
    ```

#### 8. Simulate a Purchase

- **Method:** `POST`
- **URL:** [http://localhost:8080/purchase](http://localhost:8080/purchase)
- **Request Body:**

    ```json
    {
        "productId": 1,
        "promoCode": "SAVE50"
    }
    ```

#### 9. Sales Report

- **Description:** Provides the number of purchases and the total value by currency.
- **Method:** `GET`
- **URL:** [http://localhost:8080/report](http://localhost:8080/report)
