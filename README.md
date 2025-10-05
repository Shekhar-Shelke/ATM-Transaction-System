CREATE DATABASE atmdb;
USE atmdb;

CREATE TABLE atm_transaction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    atm_cash_balance DECIMAL(10,2)
);
