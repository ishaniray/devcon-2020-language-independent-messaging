CREATE TABLE CovidPt (
    Id int NOT NULL AUTO_INCREMENT,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255) NOT NULL,
    Age int NOT NULL,
    Gender varchar(255) NOT NULL,
    CoMorbiditiesFlag varchar(255) NOT NULL,
    DeceasedFlag varchar(255) NOT NULL,
    City varchar(255) NOT NULL,
    PRIMARY KEY (Id)
);