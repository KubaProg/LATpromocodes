INSERT INTO products (name, description, regular_price, currency) VALUES
                                                                      ('Laptop', 'A high-end gaming laptop with 16GB RAM and RTX 3070 GPU.', 1299.99, 'USD'),
                                                                      ('Smartphone', 'A flagship smartphone with 5G connectivity and OLED display.', 799.99, 'USD'),
                                                                      ('Smartwatch', 'A smartwatch with health monitoring and GPS capabilities.', 199.99, 'EUR'),
                                                                      ('TV', 'A 55-inch 4K OLED TV with HDR and Dolby Vision support.', 1099.99, 'USD'),
                                                                      ('Headphones', 'Noise-canceling over-ear headphones with Bluetooth.', 149.99, 'PLN'),
                                                                      ('Tablet', 'A tablet with a 10-inch display and 128GB storage.', 399.99, 'EUR'),
                                                                      ('Camera', 'A DSLR camera with a 24MP sensor and 4K video recording.', 899.99, 'USD'),
                                                                      ('Speaker', 'A portable Bluetooth speaker with 360-degree sound.', 99.99, 'USD'),
                                                                      ('Console', 'A next-gen gaming console with 4K support and a large library of games.', 499.99, 'USD'),
                                                                      ('Monitor', 'A 27-inch 4K monitor with HDR and a refresh rate of 144Hz.', 299.99, 'EUR');

INSERT INTO promo_codes_amount (code, expiration_date, currency, max_usages, current_usages, discount_amount) VALUES
                                                                                                                  ('SAVE50', '2024-12-31T23:59:59', 'USD', 100, 10, 50.00),
                                                                                                                  ('DISCOUNT20', '2024-06-30T23:59:59', 'EUR', 50, 20, 20.00),
                                                                                                                  ('HEADSET10', '2024-05-31T23:59:59', 'PLN', 30, 5, 10.00),
                                                                                                                  ('TVDEAL100', '2024-07-31T23:59:59', 'USD', 40, 15, 100.00),
                                                                                                                  ('WINTER2023', '2023-12-31T23:59:59', 'USD', 60, 50, 30.00),
                                                                                                                  ('SUMMER2023', '2023-08-01T23:59:59', 'EUR', 30, 25, 10.00);

INSERT INTO promo_codes_percentage (code, expiration_date, currency, max_usages, current_usages, discount_rate) VALUES
                                                                                                                    ('SAVE10', '2024-08-15T23:59:59', 'USD', 200, 50, 10.00),
                                                                                                                    ('ELECTRONICS15', '2024-04-30T23:59:59', 'USD', 150, 25, 15.00),
                                                                                                                    ('SMARTHOME5', '2024-09-30T23:59:59', 'EUR', 100, 40, 5.00),
                                                                                                                    ('GADGET20', '2024-03-31T23:59:59', 'USD', 120, 30, 20.00),
                                                                                                                    ('FLASHSALE2023', '2023-11-01T23:59:59', 'USD', 50, 45, 20.00),
                                                                                                                    ('AUTUMN10', '2023-09-30T23:59:59', 'EUR', 80, 70, 10.00);

INSERT INTO purchases (product_id, regular_price, date_of_purchase, discount_applied) VALUES
                                                                                          (1, 1299.99, '2024-04-01T12:00:00', 50.00), -- Using promo code "SAVE50"
                                                                                          (2, 799.99, '2024-03-01T10:30:00', 15.00), -- Using promo code "ELECTRONICS15"
                                                                                          (3, 199.99, '2023-12-01T09:00:00', 0.00), -- No promo code
                                                                                          (4, 1099.99, '2024-06-01T14:45:00', 100.00), -- Using promo code "TVDEAL100"
                                                                                          (5, 149.99, '2023-11-01T15:15:00', 0.00), -- No promo code
                                                                                          (6, 399.99, '2024-05-01T16:30:00', 20.00), -- Using promo code "DISCOUNT20"
                                                                                          (7, 899.99, '2024-08-01T17:00:00', 10.00), -- Using promo code "SAVE10"
                                                                                          (8, 99.99, '2024-02-01T11:00:00', 0.00), -- No promo code
                                                                                          (9, 499.99, '2024-01-01T12:00:00', 0.00), -- No promo code
                                                                                          (10, 299.99, '2023-09-01T13:00:00', 10.00); -- Using promo code "AUTUMN10"
