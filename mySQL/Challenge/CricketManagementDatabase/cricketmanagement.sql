CREATE DATABASE IF NOT EXISTS CricketManagement;
USE CricketManagement;
CREATE TABLE IF NOT EXISTS Country (
    CountryID SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    CountryName VARCHAR(100) NOT NULL, -- Assuming country names won't be longer than 100 characters
    Ranking TINYINT UNSIGNED -- Assuming ranking is a number from 1-100
);
CREATE TABLE IF NOT EXISTS Stadium (
    StadiumID MEDIUMINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    StadiumName VARCHAR(100) NOT NULL, -- Assuming stadium names won't be longer than 100 characters
    StadiumLocation VARCHAR(100) NOT NULL, -- Adjusting for longer city names just in case
    SeatingCapacity MEDIUMINT UNSIGNED, -- Large enough for seat counts, up to 16 million
    CountryID SMALLINT UNSIGNED,
    FOREIGN KEY (CountryID) REFERENCES Country(CountryID)
);
CREATE TABLE IF NOT EXISTS Club (
    ClubID MEDIUMINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    ClubName VARCHAR(100) NOT NULL, -- Club names are generally not too long
    FoundedYear YEAR, -- 'YEAR' data type is ideal for storing years
    CoachFirstName VARCHAR(50), -- 50 characters are enough for a first name
    CoachLastName VARCHAR(50), -- 50 characters are enough for a last name
    StadiumID MEDIUMINT UNSIGNED,
    FOREIGN KEY (StadiumID) REFERENCES Stadium(StadiumID)
);
CREATE TABLE IF NOT EXISTS Player (
    PlayerID MEDIUMINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Age TINYINT UNSIGNED, -- Age in years, TINYINT is sufficient (0 to 255)
    PlayerRole ENUM('Batsman', 'Bowler', 'Wicketkeeper', 'All-rounder') NOT NULL,
    Address VARCHAR(255), -- Addresses can vary in length, so a standard VARCHAR(255) is used
    ClubID MEDIUMINT UNSIGNED,
    FOREIGN KEY (ClubID) REFERENCES Club(ClubID)
);

CREATE TABLE IF NOT EXISTS Game (
    GameID MEDIUMINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    GameDate DATETIME NOT NULL,
    HomeClubID MEDIUMINT UNSIGNED,
    AwayClubID MEDIUMINT UNSIGNED,
    HasHomeWon ENUM('Won', 'Lost', 'Draw', 'Not Played') DEFAULT 'Not Played',
    FOREIGN KEY (HomeClubID) REFERENCES Club(ClubID),
    FOREIGN KEY (AwayClubID) REFERENCES Club(ClubID)
);
		
					-- ALTER TABLES TO HAVE UNIQUE VALUES WITHOUT DUPLICATES

/*
ALTER TABLE Country ADD UNIQUE (CountryName);
ALTER TABLE Stadium ADD UNIQUE (StadiumName, CountryID);
ALTER TABLE Club ADD UNIQUE (ClubName, StadiumID);
ALTER TABLE Player ADD UNIQUE (FirstName, LastName, ClubID);
*/

					-- ADD DATA IN COUNTRIES AND THEIR WORLD POSITION CONSIDERING CURRENT LIVE RANKING

/*
INSERT INTO Country (CountryName, Ranking) VALUES 
('India', 1),
('Australia', 2),
('England', 3),
('New Zealand', 4),
('South Africa', 5),
('Pakistan', 6),
('Bangladesh', 7),
('Sri Lanka', 8),
('West Indies', 9),
('Afghanistan', 10),
*/

					-- ADD REAL STADIUMS FOR EACH COUNTRY

/*
-- India
INSERT INTO Stadium (StadiumName, StadiumLocation, SeatingCapacity, CountryID) VALUES 
('Eden Gardens', 'Kolkata', 66000, (SELECT CountryID FROM Country WHERE CountryName = 'India')),
('Wankhede Stadium', 'Mumbai', 33108, (SELECT CountryID FROM Country WHERE CountryName = 'India'));
	/*
	--('M. A. Chidambaram Stadium', 'Chennai', 50000, (SELECT CountryID FROM Country WHERE CountryName = 'India')),
	--('Arun Jaitley Stadium', 'Delhi', 41820, (SELECT CountryID FROM Country WHERE CountryName = 'India')),
	--('M. Chinnaswamy Stadium', 'Bengaluru', 40000, (SELECT CountryID FROM Country WHERE CountryName = 'India'));
	*/

/*
-- Australia
INSERT INTO Stadium (StadiumName, StadiumLocation, SeatingCapacity, CountryID) VALUES 
('Melbourne Cricket Ground', 'Melbourne', 100024, (SELECT CountryID FROM Country WHERE CountryName = 'Australia')),
('Sydney Cricket Ground', 'Sydney', 48601, (SELECT CountryID FROM Country WHERE CountryName = 'Australia'));
	/*
	--('Adelaide Oval', 'Adelaide', 53500, (SELECT CountryID FROM Country WHERE CountryName = 'Australia')),
	--('The Gabba', 'Brisbane', 42000, (SELECT CountryID FROM Country WHERE CountryName = 'Australia')),
	--('Perth Stadium', 'Perth', 60000, (SELECT CountryID FROM Country WHERE CountryName = 'Australia'));
    */
    
/*
-- England
INSERT INTO Stadium (StadiumName, StadiumLocation, SeatingCapacity, CountryID) VALUES 
('Lord\'s', 'London', 30000, (SELECT CountryID FROM Country WHERE CountryName = 'England')),
('Edgbaston', 'Birmingham', 25000, (SELECT CountryID FROM Country WHERE CountryName = 'England'));
	/*
	--('The Oval', 'London', 25500, (SELECT CountryID FROM Country WHERE CountryName = 'England')),
	--('Old Trafford', 'Manchester', 26000, (SELECT CountryID FROM Country WHERE CountryName = 'England')),
	--('Headingley', 'Leeds', 18350, (SELECT CountryID FROM Country WHERE CountryName = 'England'));
	*/

/*
-- New Zealand
INSERT INTO Stadium (StadiumName, StadiumLocation, SeatingCapacity, CountryID) VALUES 
('Eden Park', 'Auckland', 50000, (SELECT CountryID FROM Country WHERE CountryName = 'New Zealand')),
('Hagley Oval', 'Christchurch', 18000, (SELECT CountryID FROM Country WHERE CountryName = 'New Zealand'));
	/*
	--('Seddon Park', 'Hamilton', 10000, (SELECT CountryID FROM Country WHERE CountryName = 'New Zealand')),
	--('Basin Reserve', 'Wellington', 11814, (SELECT CountryID FROM Country WHERE CountryName = 'New Zealand')),
	--('University Oval', 'Dunedin', 3500, (SELECT CountryID FROM Country WHERE CountryName = 'New Zealand'));
	*/

/*
-- South Africa
INSERT INTO Stadium (StadiumName, StadiumLocation, SeatingCapacity, CountryID) VALUES 
('Newlands Cricket Ground', 'Cape Town', 25000, (SELECT CountryID FROM Country WHERE CountryName = 'South Africa')),
('Wanderers Stadium', 'Johannesburg', 34000, (SELECT CountryID FROM Country WHERE CountryName = 'South Africa'));
	/*
	--('Kingsmead Cricket Ground', 'Durban', 25000, (SELECT CountryID FROM Country WHERE CountryName = 'South Africa')),
	--('SuperSport Park', 'Centurion', 22000, (SELECT CountryID FROM Country WHERE CountryName = 'South Africa')),
	--('St George\'s Park', 'Port Elizabeth', 19000, (SELECT CountryID FROM Country WHERE CountryName = 'South Africa'));
	*/

/*
-- Pakistan
INSERT INTO Stadium (StadiumName, StadiumLocation, SeatingCapacity, CountryID) VALUES 
('Gaddafi Stadium', 'Lahore', 27000, (SELECT CountryID FROM Country WHERE CountryName = 'Pakistan')),
('National Stadium', 'Karachi', 34228, (SELECT CountryID FROM Country WHERE CountryName = 'Pakistan'));
	/*
	--('Rawalpindi Cricket Stadium', 'Rawalpindi', 15000, (SELECT CountryID FROM Country WHERE CountryName = 'Pakistan')),
	--('Multan Cricket Stadium', 'Multan', 35000, (SELECT CountryID FROM Country WHERE CountryName = 'Pakistan')),
	--('Arbab Niaz Stadium', 'Peshawar', 20000, (SELECT CountryID FROM Country WHERE CountryName = 'Pakistan'));
	*/

/*
-- Bangladesh
INSERT INTO Stadium (StadiumName, StadiumLocation, SeatingCapacity, CountryID) VALUES 
('Sher-e-Bangla National Cricket Stadium', 'Dhaka', 25600, (SELECT CountryID FROM Country WHERE CountryName = 'Bangladesh')),
('Zahur Ahmed Chowdhury Stadium', 'Chattogram', 20000, (SELECT CountryID FROM Country WHERE CountryName = 'Bangladesh'));
	/*
	--('Sylhet International Cricket Stadium', 'Sylhet', 18500, (SELECT CountryID FROM Country WHERE CountryName = 'Bangladesh')),
	--('Sheikh Abu Naser Stadium', 'Khulna', 15000, (SELECT CountryID FROM Country WHERE CountryName = 'Bangladesh')),
	--('Khan Shaheb Osman Ali Stadium', 'Fatullah', 25000, (SELECT CountryID FROM Country WHERE CountryName = 'Bangladesh'));
	*/

/*
-- Sri Lanka
INSERT INTO Stadium (StadiumName, StadiumLocation, SeatingCapacity, CountryID) VALUES 
('R. Premadasa Stadium', 'Colombo', 35000, (SELECT CountryID FROM Country WHERE CountryName = 'Sri Lanka')),
('Galle International Stadium', 'Galle', 35000, (SELECT CountryID FROM Country WHERE CountryName = 'Sri Lanka'));
	/*
	--('Pallekele International Cricket Stadium', 'Kandy', 35000, (SELECT CountryID FROM Country WHERE CountryName = 'Sri Lanka')),
	--('Dambulla International Stadium', 'Dambulla', 16800, (SELECT CountryID FROM Country WHERE CountryName = 'Sri Lanka')),
	--('Sinhalese Sports Club', 'Colombo', 10000, (SELECT CountryID FROM Country WHERE CountryName = 'Sri Lanka'));
	*/

/*
-- West Indies (Using a generic ID for West Indies; adjust based on your country entries for specific islands)
INSERT INTO Stadium (StadiumName, StadiumLocation, SeatingCapacity, CountryID) VALUES 
('Kensington Oval', 'Bridgetown, Barbados', 28000, (SELECT CountryID FROM Country WHERE CountryName = 'West Indies')),
('Queen\'s Park Oval', 'Port of Spain, Trinidad', 20000, (SELECT CountryID FROM Country WHERE CountryName = 'West Indies'));
	/*
	--('Sabina Park', 'Kingston, Jamaica', 20000, (SELECT CountryID FROM Country WHERE CountryName = 'West Indies')),
	--('Sir Vivian Richards Stadium', 'North Sound, Antigua', 10000, (SELECT CountryID FROM Country WHERE CountryName = 'West Indies')),
	--('Daren Sammy National Cricket Stadium', 'Gros Islet, St Lucia', 15000, (SELECT CountryID FROM Country WHERE CountryName = 'West Indies'));
	*/

/*
-- Afghanistan
INSERT INTO Stadium (StadiumName, StadiumLocation, SeatingCapacity, CountryID) VALUES 

('Kabul International Cricket Stadium', 'Kabul', 6000, (SELECT CountryID FROM Country WHERE CountryName = 'Afghanistan')),
('Kandahar International Cricket Stadium', 'Kandahar', 15000, (SELECT CountryID FROM Country WHERE CountryName = 'Afghanistan'));
	/*
	--('Balkh Cricket Stadium', 'Mazar-i-Sharif', 15000, (SELECT CountryID FROM Country WHERE CountryName = 'Afghanistan')),
	--('Sharjah Cricket Stadium', 'Sharjah', 17000, (SELECT CountryID FROM Country WHERE CountryName = 'Afghanistan')),
	--('Sheikh Zayed Stadium', 'Abu Dhabi', 20000, (SELECT CountryID FROM Country WHERE CountryName = 'Afghanistan'));
	*/


					-- ADD FICTIONAL CLUBS FOR EACH COUNTRY, ONE PER STADIUM

/*
-- India                    
INSERT INTO Club (ClubName, FoundedYear, CoachFirstName, CoachLastName, StadiumID) VALUES 
('Kolkata Tigers', 2000, 'Rahul', 'Banerjee', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Eden Gardens')),
('Mumbai Warriors', 2001, 'Vijay', 'Patel', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Wankhede Stadium'));
	/*
	--('Chennai Kings', 2002, 'Suresh', 'Krishnamurthy', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'M. A. Chidambaram Stadium')),
	--('Delhi Daredevils', 2003, 'Amit', 'Sharma', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Arun Jaitley Stadium')),
	--('Bengaluru Royals', 2004, 'Anil', 'Kumar', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'M. Chinnaswamy Stadium'));
	*/

/*
-- Australia
INSERT INTO Club (ClubName, FoundedYear, CoachFirstName, CoachLastName, StadiumID) VALUES 
('Melbourne Mavericks', 2005, 'John', 'Smith', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Melbourne Cricket Ground')),
('Sydney Siders', 2006, 'David', 'Wilson', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Sydney Cricket Ground'));
	/*
	--('Adelaide Aces', 2007, 'Michael', 'Brown', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Adelaide Oval')),
	--('Brisbane Bashers', 2008, 'Chris', 'Martin', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'The Gabba')),
	--('Perth Pioneers', 2009, 'Luke', 'Taylor', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Perth Stadium'));
	*/

/*
-- England
INSERT INTO Club (ClubName, FoundedYear, CoachFirstName, CoachLastName, StadiumID) VALUES 
('London Lions', 2010, 'James', 'Anderson', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Lord\'s')),
('Birmingham Bulls', 2011, 'Edward', 'Johnson', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Edgbaston'));
	/*
	--('Manchester Monarchs', 2012, 'William', 'Davis', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Old Trafford')),
	--('Leeds Legends', 2013, 'George', 'Baker', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Headingley')),
	--('Nottingham Knights', 2014, 'Henry', 'Wilson', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Trent Bridge'));
	*/

/*
-- New Zeland
INSERT INTO Club (ClubName, FoundedYear, CoachFirstName, CoachLastName, StadiumID) VALUES 
('Auckland Albatross', 2000, 'Jack', 'Taylor', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Eden Park')),
('Christchurch Crusaders', 2001, 'Liam', 'Scott', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Hagley Oval'));
	/*
	--('Hamilton Hawks', 2002, 'Mason', 'Clark', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Seddon Park')),
	--('Wellington Wizards', 2003, 'Ethan', 'Mitchell', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Basin Reserve')),
	--('Dunedin Dragons', 2004, 'Noah', 'Bell', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'University Oval'));
	*/

/*
-- South Africa
INSERT INTO Club (ClubName, FoundedYear, CoachFirstName, CoachLastName, StadiumID) VALUES 
('Cape Town Cobras', 2005, 'Daniel', 'van der Merwe', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Newlands Cricket Ground')),
('Johannesburg Jaguars', 2006, 'Oliver', 'Botha', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Wanderers Stadium'));
	/*
	--('Durban Dolphins', 2007, 'Elijah', 'Naidoo', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Kingsmead Cricket Ground')),
	--('Centurion Spartans', 2008, 'Benjamin', 'Pretorius', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'SuperSport Park')),
	--('Port Elizabeth Pythons', 2009, 'Lucas', 'Steyn', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'St George\'s Park'));
	*/

/*
-- Pakistan
INSERT INTO Club (ClubName, FoundedYear, CoachFirstName, CoachLastName, StadiumID) VALUES 
('Lahore Lions', 2010, 'Muhammad', 'Ali', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Gaddafi Stadium')),
('Karachi Kings', 2011, 'Ahmed', 'Khan', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'National Stadium'));
	/*
	--('Rawalpindi Rams', 2012, 'Omar', 'Hussain', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Rawalpindi Cricket Stadium')),
	--('Multan Monarchs', 2013, 'Adil', 'Iqbal', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Multan Cricket Stadium')),
	--('Peshawar Panthers', 2014, 'Faisal', 'Ahmed', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Arbab Niaz Stadium'));
	*/

/*
-- Bangladesh
INSERT INTO Club (ClubName, FoundedYear, CoachFirstName, CoachLastName, StadiumID) VALUES 
('Dhaka Dynamos', 2015, 'Rahim', 'Chowdhury', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Sher-e-Bangla National Cricket Stadium')),
('Chittagong Challengers', 2016, 'Samiul', 'Islam', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Zahur Ahmed Chowdhury Stadium'));
	/*
	--('Sylhet Strikers', 2017, 'Nasir', 'Hossain', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Sylhet International Cricket Stadium')),
	--('Khulna Tigers', 2018, 'Mahbub', 'Rahman', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Sheikh Abu Naser Stadium')),
	--('Rajshahi Royals', 2019, 'Tariq', 'Hassan', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Khan Shaheb Osman Ali Stadium'));
	*/

/*
-- Sri Lanka
INSERT INTO Club (ClubName, FoundedYear, CoachFirstName, CoachLastName, StadiumID) VALUES 
('Colombo Kings', 2020, 'Dinuka', 'Perera', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'R. Premadasa Stadium')),
('Galle Gladiators', 2021, 'Lahiru', 'Fernando', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Galle International Stadium'));
	/*
	--('Kandy Tuskers', 2022, 'Niroshan', 'Silva', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Pallekele International Cricket Stadium')),
	--('Dambulla Viikings', 2023, 'Kasun', 'Rajapaksa', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Dambulla International Stadium')),
	--('Jaffna Stallions', 2024, 'Tharindu', 'Jayasuriya', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Sinhalese Sports Club'));
	*/

/*
-- West Indies
INSERT INTO Club (ClubName, FoundedYear, CoachFirstName, CoachLastName, StadiumID) VALUES 
('Barbados Tridents', 2005, 'Jason', 'Holder', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Kensington Oval')),
('Trinidad Knight Riders', 2006, 'Kieron', 'Pollard', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Queen\'s Park Oval'));
	/*
	--('Jamaica Tallawahs', 2007, 'Chris', 'Gayle', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Sabina Park')),
	--('Antigua Hawksbills', 2008, 'Curtly', 'Ambrose', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Sir Vivian Richards Stadium')),
	--('St Lucia Zouks', 2009, 'Darren', 'Sammy', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Daren Sammy National Cricket Stadium'));
	*/

/*
-- Afghanistan
INSERT INTO Club (ClubName, FoundedYear, CoachFirstName, CoachLastName, StadiumID) VALUES 
('Kabul Eagles', 2010, 'Asghar', 'Afghan', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Kabul International Cricket Stadium')),
('Kandahar Knights', 2011, 'Mohammad', 'Nabi', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Kandahar International Cricket Stadium'));
	/*
	--('Sharjah Warriors', 2012, 'Rashid', 'Khan', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Sharjah Cricket Stadium')),
	--('Balkh Legends', 2014, 'Nawroz', 'Mangal', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Balkh Cricket Stadium')),
	--('Abu Dhabi Falcons', 2013, 'Gulbadin', 'Naib', (SELECT StadiumID FROM Stadium WHERE StadiumName = 'Sheikh Zayed Stadium'));
	*/


					-- ADD REAL LIFE PLAYERS FOR EACH FICTIONAL CLUB, 15 PER CLUB WHERE POSSIBLE
				
/*
-- Inserting players into the Player table for Kolkata Tigers (INDIA)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Virat', 'Kohli', 32, 'Batsman', 'Delhi', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('Rohit', 'Sharma', 34, 'Batsman', 'Mumbai', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('Shikhar', 'Dhawan', 35, 'Batsman', 'Delhi', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('Ajinkya', 'Rahane', 33, 'Batsman', 'Mumbai', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('KL', 'Rahul', 29, 'Wicketkeeper', 'Karnataka', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('Rishabh', 'Pant', 24, 'Wicketkeeper', 'Uttarakhand', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('Ravindra', 'Jadeja', 32, 'All-rounder', 'Saurashtra', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('Hardik', 'Pandya', 28, 'All-rounder', 'Baroda', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('Jasprit', 'Bumrah', 28, 'Bowler', 'Gujarat', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('Mohammed', 'Shami', 31, 'Bowler', 'Uttar Pradesh', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('Bhuvneshwar', 'Kumar', 31, 'Bowler', 'Uttar Pradesh', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('Kuldeep', 'Yadav', 26, 'Bowler', 'Uttar Pradesh', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('Yuzvendra', 'Chahal', 31, 'Bowler', 'Haryana', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('Ishant', 'Sharma', 33, 'Bowler', 'Delhi', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers')),
('Umesh', 'Yadav', 33, 'Bowler', 'Nagpur', (SELECT ClubID FROM Club WHERE ClubName = 'Kolkata Tigers'));
*/

/*
-- Inserting players into the Player table for Mumbai Warriors (INDIA)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Shreyas', 'Iyer', 27, 'Batsman', 'Mumbai', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('Prithvi', 'Shaw', 22, 'Batsman', 'Mumbai', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('Suryakumar', 'Yadav', 31, 'Batsman', 'Mumbai', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('Mayank', 'Agarwal', 30, 'Batsman', 'Karnataka', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('Dinesh', 'Karthik', 36, 'Wicketkeeper', 'Tamil Nadu', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('Sanju', 'Samson', 27, 'Wicketkeeper', 'Kerala', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('Kedar', 'Jadhav', 36, 'All-rounder', 'Maharashtra', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('Ravichandran', 'Ashwin', 35, 'All-rounder', 'Tamil Nadu', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('Navdeep', 'Saini', 29, 'Bowler', 'Delhi', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('Deepak', 'Chahar', 29, 'Bowler', 'Rajasthan', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('T', 'Natarajan', 30, 'Bowler', 'Tamil Nadu', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('Varun', 'Chakravarthy', 30, 'Bowler', 'Tamil Nadu', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('Washington', 'Sundar', 22, 'All-rounder', 'Tamil Nadu', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('Shardul', 'Thakur', 30, 'Bowler', 'Maharashtra', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors')),
('Axar', 'Patel', 28, 'All-rounder', 'Gujarat', (SELECT ClubID FROM Club WHERE ClubName = 'Mumbai Warriors'));
*/

/*
-- Inserting players into the Player table for Melbourne Mavericks (AUSTRALIA)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('David', 'Warner', 35, 'Batsman', 'New South Wales', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('Steve', 'Smith', 32, 'Batsman', 'New South Wales', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('Aaron', 'Finch', 35, 'Batsman', 'Victoria', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('Marnus', 'Labuschagne', 27, 'Batsman', 'Queensland', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('Alex', 'Carey', 30, 'Wicketkeeper', 'South Australia', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('Tim', 'Paine', 37, 'Wicketkeeper', 'Tasmania', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('Glenn', 'Maxwell', 33, 'All-rounder', 'Victoria', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('Mitchell', 'Marsh', 30, 'All-rounder', 'Western Australia', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('Pat', 'Cummins', 28, 'Bowler', 'New South Wales', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('Mitchell', 'Starc', 31, 'Bowler', 'New South Wales', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('Josh', 'Hazlewood', 30, 'Bowler', 'New South Wales', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('Nathan', 'Lyon', 34, 'Bowler', 'New South Wales', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('Adam', 'Zampa', 29, 'Bowler', 'South Australia', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('James', 'Pattinson', 31, 'Bowler', 'Victoria', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks')),
('Jhye', 'Richardson', 25, 'Bowler', 'Western Australia', (SELECT ClubID FROM Club WHERE ClubName = 'Melbourne Mavericks'));
*/

/*
-- Inserting players into the Player table for Sydney Siders (AUSTRALIA)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Phillip', 'Hughes', 25, 'Batsman', 'New South Wales', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')), -- Note: Included as a tribute
('Shane', 'Watson', 40, 'All-rounder', 'Queensland', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')),
('Usman', 'Khawaja', 35, 'Batsman', 'Queensland', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')),
('Matthew', 'Wade', 34, 'Wicketkeeper', 'Tasmania', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')),
('Travis', 'Head', 28, 'Batsman', 'South Australia', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')),
('Peter', 'Handscomb', 30, 'Batsman', 'Victoria', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')),
('Marcus', 'Stoinis', 32, 'All-rounder', 'Western Australia', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')),
('Ashton', 'Agar', 28, 'All-rounder', 'Western Australia', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')),
('Michael', 'Neser', 31, 'Bowler', 'Queensland', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')),
('Kane', 'Richardson', 30, 'Bowler', 'South Australia', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')),
('Andrew', 'Tye', 35, 'Bowler', 'Western Australia', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')),
('Sean', 'Abbott', 29, 'Bowler', 'New South Wales', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')),
('Jason', 'Behrendorff', 31, 'Bowler', 'Western Australia', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')),
('Nathan', 'Coulter-Nile', 34, 'Bowler', 'Western Australia', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders')),
('Daniel', 'Sams', 29, 'All-rounder', 'New South Wales', (SELECT ClubID FROM Club WHERE ClubName = 'Sydney Siders'));
*/

/*
-- Inserting players into the Player table for London Lions (ENGLAND)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Joe', 'Root', 30, 'Batsman', 'Yorkshire', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('Ben', 'Stokes', 29, 'All-rounder', 'Durham', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('Jos', 'Buttler', 30, 'Wicketkeeper', 'Lancashire', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('Jonny', 'Bairstow', 31, 'Wicketkeeper', 'Yorkshire', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('Eoin', 'Morgan', 34, 'Batsman', 'Middlesex', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('Jason', 'Roy', 30, 'Batsman', 'Surrey', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('Moeen', 'Ali', 34, 'All-rounder', 'Worcestershire', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('Chris', 'Woakes', 32, 'All-rounder', 'Warwickshire', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('Jofra', 'Archer', 26, 'Bowler', 'Sussex', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('Stuart', 'Broad', 35, 'Bowler', 'Nottinghamshire', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('James', 'Anderson', 38, 'Bowler', 'Lancashire', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('Mark', 'Wood', 31, 'Bowler', 'Durham', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('Sam', 'Curran', 23, 'All-rounder', 'Surrey', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('Adil', 'Rashid', 33, 'Bowler', 'Yorkshire', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions')),
('Olly', 'Stone', 27, 'Bowler', 'Warwickshire', (SELECT ClubID FROM Club WHERE ClubName = 'London Lions'));
*/

/*
-- Inserting players into the Player table for Birmingham Bulls (ENGLAND)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Rory', 'Burns', 30, 'Batsman', 'Surrey', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('Dominic', 'Sibley', 25, 'Batsman', 'Warwickshire', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('Zak', 'Crawley', 23, 'Batsman', 'Kent', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('Ollie', 'Pope', 23, 'Batsman', 'Surrey', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('Dan', 'Lawrence', 24, 'Batsman', 'Essex', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('James', 'Bracey', 24, 'Wicketkeeper', 'Gloucestershire', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('Tom', 'Curran', 26, 'All-rounder', 'Surrey', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('David', 'Willey', 31, 'All-rounder', 'Yorkshire', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('Chris', 'Jordan', 32, 'Bowler', 'Surrey', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('Tom', 'Banton', 22, 'Batsman', 'Somerset', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('Liam', 'Dawson', 31, 'All-rounder', 'Hampshire', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('Jake', 'Ball', 30, 'Bowler', 'Nottinghamshire', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('Saqib', 'Mahmood', 24, 'Bowler', 'Lancashire', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('Phil', 'Salt', 24, 'Batsman', 'Sussex', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls')),
('Matt', 'Parkinson', 24, 'Bowler', 'Lancashire', (SELECT ClubID FROM Club WHERE ClubName = 'Birmingham Bulls'));
*/

/*
-- Inserting players into the Player table for Auckland Albatross (NEW ZELAND)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Kane', 'Williamson', 31, 'Batsman', 'Tauranga', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('Ross', 'Taylor', 37, 'Batsman', 'Wellington', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('Martin', 'Guptill', 35, 'Batsman', 'Auckland', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('Tom', 'Latham', 29, 'Wicketkeeper', 'Christchurch', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('Henry', 'Nicholls', 29, 'Batsman', 'Christchurch', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('Colin', 'de Grandhomme', 35, 'All-rounder', 'Harare', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('Mitchell', 'Santner', 29, 'All-rounder', 'Hamilton', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('Tim', 'Southee', 32, 'Bowler', 'Whangarei', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('Trent', 'Boult', 32, 'Bowler', 'Rotorua', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('Kyle', 'Jamieson', 27, 'Bowler', 'Auckland', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('Lockie', 'Ferguson', 30, 'Bowler', 'Auckland', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('Matt', 'Henry', 29, 'Bowler', 'Christchurch', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('James', 'Neesham', 31, 'All-rounder', 'Auckland', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('Ish', 'Sodhi', 28, 'Bowler', 'Ludhiana', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross')),
('Devon', 'Conway', 30, 'Batsman', 'Johannesburg', (SELECT ClubID FROM Club WHERE ClubName = 'Auckland Albatross'));
*/

/*
-- Inserting players into the Player table for Christchurch Crusaders (NEW ZELAND)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Tom', 'Blundell', 31, 'Wicketkeeper', 'Wellington', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('Will', 'Young', 29, 'Batsman', 'New Plymouth', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('Daryl', 'Mitchell', 30, 'All-rounder', 'Hamilton', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('Glenn', 'Phillips', 25, 'Batsman', 'Auckland', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('Mark', 'Chapman', 27, 'Batsman', 'Hong Kong', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('Doug', 'Bracewell', 31, 'All-rounder', 'Napier', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('Adam', 'Milne', 29, 'Bowler', 'Palmerston North', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('Hamish', 'Bennett', 34, 'Bowler', 'Timaru', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('Todd', 'Astle', 35, 'Bowler', 'Christchurch', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('Ajaz', 'Patel', 33, 'Bowler', 'Mumbai', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('Neil', 'Wagner', 35, 'Bowler', 'Pretoria', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('BJ', 'Watling', 36, 'Wicketkeeper', 'Durban', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('Corey', 'Anderson', 31, 'All-rounder', 'Christchurch', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('Jeet', 'Raval', 33, 'Batsman', 'Gujarat', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders')),
('Colin', 'Munro', 34, 'Batsman', 'Durban', (SELECT ClubID FROM Club WHERE ClubName = 'Christchurch Crusaders'));
*/

/*
-- Inserting players into the Player table for Cape Town Cobras (SOUTH AFRICA)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Quinton', 'de Kock', 28, 'Wicketkeeper', 'Johannesburg', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('Faf', 'du Plessis', 37, 'Batsman', 'Pretoria', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('AB', 'de Villiers', 37, 'Batsman', 'Pretoria', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('Hashim', 'Amla', 38, 'Batsman', 'Durban', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('Jacques', 'Kallis', 45, 'All-rounder', 'Cape Town', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('David', 'Miller', 32, 'Batsman', 'Pietermaritzburg', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('JP', 'Duminy', 37, 'All-rounder', 'Cape Town', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('Kagiso', 'Rabada', 26, 'Bowler', 'Johannesburg', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('Dale', 'Steyn', 38, 'Bowler', 'Phalaborwa', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('Morne', 'Morkel', 37, 'Bowler', 'Vereeniging', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('Vernon', 'Philander', 36, 'Bowler', 'Cape Town', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('Imran', 'Tahir', 42, 'Bowler', 'Lahore', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('Anrich', 'Nortje', 28, 'Bowler', 'Uitenhage', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('Heinrich', 'Klaasen', 30, 'Wicketkeeper', 'Pretoria', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras')),
('Andile', 'Phehlukwayo', 25, 'All-rounder', 'Durban', (SELECT ClubID FROM Club WHERE ClubName = 'Cape Town Cobras'));
*/

/*
-- Inserting players into the Player table for Johannesburg Jaguars (SOUTH AFRICA)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Temba', 'Bavuma', 31, 'Batsman', 'Cape Town', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('Dean', 'Elgar', 34, 'Batsman', 'Welkom', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('Aiden', 'Markram', 27, 'Batsman', 'Centurion', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('Rassie', 'van der Dussen', 32, 'Batsman', 'Pretoria', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('Kyle', 'Verreynne', 24, 'Wicketkeeper', 'Cape Town', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('Keshav', 'Maharaj', 31, 'Bowler', 'Durban', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('Chris', 'Morris', 34, 'All-rounder', 'Pretoria', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('Lungi', 'Ngidi', 25, 'Bowler', 'Durban', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('Beuran', 'Hendricks', 31, 'Bowler', 'Bellville', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('Tabraiz', 'Shamsi', 31, 'Bowler', 'Johannesburg', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('Dwaine', 'Pretorius', 32, 'All-rounder', 'Randfontein', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('Reeza', 'Hendricks', 32, 'Batsman', 'Kimberley', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('Janneman', 'Malan', 25, 'Batsman', 'Nelspruit', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('George', 'Linde', 29, 'All-rounder', 'Cape Town', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars')),
('Wiaan', 'Mulder', 23, 'All-rounder', 'Johannesburg', (SELECT ClubID FROM Club WHERE ClubName = 'Johannesburg Jaguars'));
*/

/*
-- Inserting players into the Player table for Lahore Lions (PAKISTAN)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Babar', 'Azam', 27, 'Batsman', 'Lahore', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Mohammad', 'Rizwan', 29, 'Wicketkeeper', 'Peshawar', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Fakhar', 'Zaman', 31, 'Batsman', 'Mardan', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Imam-ul-Haq', 'Unknown' , 25, 'Batsman', 'Lahore', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Shoaib', 'Malik', 39, 'All-rounder', 'Sialkot', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Mohammad', 'Hafeez', 41, 'All-rounder', 'Sargodha', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Shaheen', 'Afridi', 21, 'Bowler', 'Khyber', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Hasan', 'Ali', 27, 'Bowler', 'Gujranwala', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Haris', 'Rauf', 27, 'Bowler', 'Rawalpindi', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Mohammad', 'Nawaz', 27, 'All-rounder', 'Rawalpindi', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Faheem', 'Ashraf', 27, 'All-rounder', 'Kasur', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Shadab', 'Khan', 23, 'All-rounder', 'Mianwali', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Imad', 'Wasim', 32, 'All-rounder', 'Swansea', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Wahab', 'Riaz', 36, 'Bowler', 'Lahore', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions')),
('Yasir', 'Shah', 35, 'Bowler', 'Swabi', (SELECT ClubID FROM Club WHERE ClubName = 'Lahore Lions'));
*/

/*
-- Inserting players into the Player table for Karachi Kings (PAKISTAN)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Sarfaraz', 'Ahmed', 34, 'Wicketkeeper', 'Karachi', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Asad', 'Shafiq', 35, 'Batsman', 'Karachi', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Azhar', 'Ali', 36, 'Batsman', 'Lahore', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Abid', 'Ali', 34, 'Batsman', 'Lahore', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Fawad', 'Alam', 36, 'Batsman', 'Karachi', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Saud', 'Shakeel', 26, 'Batsman', 'Karachi', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Mohammad', 'Amir', 29, 'Bowler', 'Gujjar Khan', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Naseem', 'Shah', 18, 'Bowler', 'Lower Dir', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Usman', 'Shinwari', 27, 'Bowler', 'Khyber Agency', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Sohail', 'Khan', 37, 'Bowler', 'Sialkot', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Rumman', 'Raees', 30, 'Bowler', 'Karachi', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Iftikhar', 'Ahmed', 31, 'All-rounder', 'Peshawar', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Khushdil', 'Shah', 26, 'Batsman', 'Bannu', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Zafar', 'Gohar', 26, 'All-rounder', 'Lahore', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings')),
('Agha', 'Salman', 27, 'All-rounder', 'Lahore', (SELECT ClubID FROM Club WHERE ClubName = 'Karachi Kings'));
*/

/*
-- Inserting players into the Player table for Dhaka Dynamos (BANGLADESH)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Tamim', 'Iqbal', 32, 'Batsman', 'Chittagong', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Mushfiqur', 'Rahim', 34, 'Wicketkeeper', 'Bogra', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Shakib', 'Al Hasan', 34, 'All-rounder', 'Magura', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Mahmudullah', 'Riyad', 35, 'All-rounder', 'Mymensingh', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Mashrafe', 'Mortaza', 38, 'Bowler', 'Narail', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Litton', 'Das', 27, 'Wicketkeeper', 'Dinajpur', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Mustafizur', 'Rahman', 26, 'Bowler', 'Satkhira', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Mohammad', 'Mithun', 30, 'Batsman', 'Kishoreganj', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Taskin', 'Ahmed', 26, 'Bowler', 'Dhaka', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Mehidy', 'Hasan', 24, 'All-rounder', 'Khulna', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Rubel', 'Hossain', 31, 'Bowler', 'Bagerhat', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Soumya', 'Sarkar', 28, 'All-rounder', 'Satkhira', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Najmul', 'Hossain', 22, 'Batsman', 'Rajshahi', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Nayeem', 'Hasan', 21, 'Bowler', 'Chittagong', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos')),
('Taijul', 'Islam', 29, 'Bowler', 'Natore', (SELECT ClubID FROM Club WHERE ClubName = 'Dhaka Dynamos'));
*/

/*
-- Inserting players into the Player table for Chittagong Challengers (BANGLADESH)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Imrul', 'Kayes', 34, 'Batsman', 'Meherpur', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Anamul', 'Haque', 28, 'Wicketkeeper', 'Kushtia', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Afif', 'Hossain', 22, 'All-rounder', 'Khulna', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Mohammad', 'Naim', 22, 'Batsman', 'Faridpur', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Aminul', 'Islam', 21, 'All-rounder', 'Panchagarh', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Abu', 'Jayed', 28, 'Bowler', 'Sylhet', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Al-Amin', 'Hossain', 31, 'Bowler', 'Jhenaidah', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Mahedi', 'Hasan', 26, 'All-rounder', 'Khulna', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Shoriful', 'Islam', 20, 'Bowler', 'Panchagarh', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Shamim', 'Hossain', 20, 'All-rounder', 'Gopalganj', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Ebadot', 'Hossain', 27, 'Bowler', 'Habiganj', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Hasan', 'Mahmud', 21, 'Bowler', 'Chattogram', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Mosaddek', 'Hossain', 25, 'All-rounder', 'Mymensingh', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Saif', 'Hassan', 23, 'Batsman', 'Dhaka', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers')),
('Yasir', 'Ali', 25, 'Batsman', 'Chattogram', (SELECT ClubID FROM Club WHERE ClubName = 'Chittagong Challengers'));
*/

/*
-- Inserting players into the Player table for Colombo Kings (SRI LANKA)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Dimuth', 'Karunaratne', 33, 'Batsman', 'Colombo', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Angelo', 'Mathews', 34, 'All-rounder', 'Colombo', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Kusal', 'Perera', 31, 'Wicketkeeper', 'Kalubowila', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Dinesh', 'Chandimal', 31, 'Batsman', 'Balapitiya', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Lahiru', 'Thirimanne', 32, 'Batsman', 'Moratuwa', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Kusal', 'Mendis', 26, 'Batsman', 'Moratuwa', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Suranga', 'Lakmal', 34, 'Bowler', 'Matara', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Lasith', 'Malinga', 38, 'Bowler', 'Galle', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Nuwan', 'Pradeep', 34, 'Bowler', 'Negombo', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Rangana', 'Herath', 43, 'Bowler', 'Kurunegala', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Thisara', 'Perera', 32, 'All-rounder', 'Colombo', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Wanindu', 'Hasaranga', 24, 'All-rounder', 'Galle', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Dhananjaya', 'de Silva', 30, 'All-rounder', 'Hambantota', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Kasun', 'Rajitha', 28, 'Bowler', 'Matara', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings')),
('Akila', 'Dananjaya', 28, 'Bowler', 'Panadura', (SELECT ClubID FROM Club WHERE ClubName = 'Colombo Kings'));
*/

/*
-- Inserting players into the Player table for Galle Gladiators (SRI LANKA)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Avishka', 'Fernando', 23, 'Batsman', 'Colombo', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Niroshan', 'Dickwella', 28, 'Wicketkeeper', 'Kandy', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Oshada', 'Fernando', 29, 'Batsman', 'Colombo', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Pathum', 'Nissanka', 23, 'Batsman', 'Galle', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Dasun', 'Shanaka', 30, 'All-rounder', 'Negombo', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Isuru', 'Udana', 33, 'All-rounder', 'Balangoda', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Asitha', 'Fernando', 24, 'Bowler', 'Chilaw', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Lahiru', 'Kumara', 25, 'Bowler', 'Kandy', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Lakshan', 'Sandakan', 30, 'Bowler', 'Galle', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Dilruwan', 'Perera', 39, 'Bowler', 'Panadura', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Dushmantha', 'Chameera', 30, 'Bowler', 'Ragama', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Jeffrey', 'Vandersay', 32, 'Bowler', 'Colombo', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Shehan', 'Jayasuriya', 30, 'All-rounder', 'Colombo', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Danushka', 'Gunathilaka', 30, 'Batsman', 'Panadura', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators')),
('Charith', 'Asalanka', 24, 'Batsman', 'Galle', (SELECT ClubID FROM Club WHERE ClubName = 'Galle Gladiators'));
*/

/*
-- Inserting players into the Player table for Barbados Tridents (WEST INDIES)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Kraigg', 'Brathwaite', 29, 'Batsman', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Shai', 'Hope', 28, 'Wicketkeeper', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Jason', 'Holder', 30, 'All-rounder', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Kemar', 'Roach', 33, 'Bowler', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Shane', 'Dowrich', 30, 'Wicketkeeper', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Carlos', 'Brathwaite', 33, 'All-rounder', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Roston', 'Chase', 29, 'All-rounder', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Jomel', 'Warrican', 29, 'Bowler', 'St. Vincent', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Raymon', 'Reifer', 30, 'All-rounder', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Kyle', 'Mayers', 29, 'All-rounder', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Shamarh', 'Brooks', 33, 'Batsman', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Ashley', 'Nurse', 33, 'All-rounder', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Jonathan', 'Carter', 34, 'Batsman', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Chemar', 'Holder', 24, 'Bowler', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents')),
('Joshua', 'Bishop', 22, 'Bowler', 'Barbados', (SELECT ClubID FROM Club WHERE ClubName = 'Barbados Tridents'));
*/

/*
-- Inserting players into the Player table for Trinidad Knight Riders (WEST INDIES)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Kieron', 'Pollard', 34, 'All-rounder', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Sunil', 'Narine', 33, 'All-rounder', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Lendl', 'Simmons', 36, 'Batsman', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Denesh', 'Ramdin', 36, 'Wicketkeeper', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Darren', 'Bravo', 32, 'Batsman', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Dwayne', 'Bravo', 38, 'All-rounder', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Nicholas', 'Pooran', 26, 'Wicketkeeper', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Shannon', 'Gabriel', 33, 'Bowler', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Evin', 'Lewis', 29, 'Batsman', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Khary', 'Pierre', 29, 'Bowler', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Anderson', 'Phillip', 25, 'Bowler', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Akeal', 'Hosein', 28, 'All-rounder', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Jayden', 'Seales', 20, 'Bowler', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Tion', 'Webster', 26, 'Batsman', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders')),
('Mark', 'Deyal', 25, 'All-rounder', 'Trinidad', (SELECT ClubID FROM Club WHERE ClubName = 'Trinidad Knight Riders'));
*/

/*
-- Inserting players into the Player table for Kabul Eagles (AFGHANISTAN) 
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Rashid', 'Khan', 23, 'Bowler', 'Nangarhar', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Mohammad', 'Nabi', 36, 'All-rounder', 'Logar', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Asghar', 'Afghan', 34, 'Batsman', 'Kabul', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Rahmat', 'Shah', 28, 'Batsman', 'Kunduz', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Hashmatullah', 'Shahidi', 27, 'Batsman', 'Logar', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Najibullah', 'Zadran', 29, 'Batsman', 'Paktia', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Gulbadin', 'Naib', 30, 'All-rounder', 'Kabul', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Mujeeb', 'Ur Rahman', 20, 'Bowler', 'Khost', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Naveen-ul-Haq', 'Unknown', 22, 'Bowler', 'Kabul', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Rahmanullah', 'Gurbaz', 20, 'Wicketkeeper', 'Khost', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Karim', 'Janat', 23, 'All-rounder', 'Kabul', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Afsar', 'Zazai', 28, 'Wicketkeeper', 'Jalalabad', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Samiullah', 'Shenwari', 34, 'All-rounder', 'Nangarhar', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Sharafuddin', 'Ashraf', 27, 'All-rounder', 'Faryab', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles')),
('Qais', 'Ahmad', 21, 'Bowler', 'Nangarhar', (SELECT ClubID FROM Club WHERE ClubName = 'Kabul Eagles'));
*/

/*
-- Inserting players into the Player table for Kandahar Knights (AFGHANISTAN)
INSERT INTO Player (FirstName, LastName, Age, PlayerRole, Address, ClubID) VALUES 
('Mohammad', 'Shahzad', 34, 'Wicketkeeper', 'Nangarhar', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Hazratullah', 'Zazai', 23, 'Batsman', 'Paktia', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Ibrahim', 'Zadran', 22, 'Batsman', 'Khost', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Usman', 'Ghani', 25, 'Batsman', 'Kabul', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Asghar', 'Salimkheil', 19, 'Bowler', 'Kunar', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Noor', 'Ali Zadran', 33, 'Batsman', 'Khost', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Amir', 'Hamza', 29, 'Bowler', 'Nangarhar', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Shapoor', 'Zadran', 35, 'Bowler', 'Logar', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Fareed', 'Ahmad', 26, 'Bowler', 'Paktia', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Najat', 'Masood', 22, 'Bowler', 'Nangarhar', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Ikram', 'Ali Khil', 20, 'Wicketkeeper', 'Nangarhar', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Javed', 'Ahmadi', 29, 'All-rounder', 'Nangarhar', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Nasir', 'Jamal', 27, 'Batsman', 'Kabul', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Hashmatullah', 'Shahidi', 27, 'Batsman', 'Kabul', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights')),
('Sayeed', 'Shirzad', 28, 'Bowler', 'Nangarhar', (SELECT ClubID FROM Club WHERE ClubName = 'Kandahar Knights'));
*/

					-- ADD RANDOM MATCHES

/*                    
INSERT INTO Game (GameDate, HomeClubID, AwayClubID, HasHomeWon) VALUES
('2024-01-15 19:00:00', 1, 2, 'Won'),  -- ClubID 1 won against ClubID 2
('2024-01-16 19:00:00', 3, 4, 'Lost'), --  ClubID 3 lost to ClubID 4
('2024-02-01 19:00:00', 5, 6, 'Not Played'); -- Future match between ClubID 5 and ClubID 6
*/

					-- QUERIES TESTING
                    
SELECT GameID, GameDate, HomeClubID, AwayClubID, HasHomeWon FROM Game WHERE HasHomeWon != 'Not Played';
-- Show all games that have been played
SELECT GameID, GameDate, HomeClubID, AwayClubID, HasHomeWon FROM Game WHERE HasHomeWon = 'Not Played';
-- Show all games that have not been played
SELECT Game.GameDate, HomeClub.ClubName AS HomeTeam, AwayClub.ClubName AS AwayTeam, Game.HasHomeWon FROM Game
JOIN Club AS HomeClub ON Game.HomeClubID = HomeClub.ClubID 
JOIN Club AS AwayClub ON Game.AwayClubID = AwayClub.ClubID
WHERE Game.HasHomeWon != 'Not Played';
-- Same as query one, instead of showing club ID show actual name -- CHECK IF THIS HAS BEEN MADE CORRECTLY
/*
	-- Print a match showing the stadium name depending on the home team that has played
*/


SELECT CountryName, Ranking FROM Country ORDER BY Ranking;
-- All countries and ranking from the list of countries ordered by ranking

SELECT Player.FirstName, Player.LastName FROM Player JOIN Club ON Player.ClubID = Club.ClubID WHERE Club.ClubName = 'Mumbai Warriors';
-- Select all player first and last name from the player list inner joined with the club where player ID matches with the club ID where the name of the club is 

SELECT StadiumName, SeatingCapacity FROM Stadium JOIN Country ON Stadium.CountryID = Country.CountryID WHERE Country.CountryName = 'Australia';
-- Select name of the stadium and capacity from the stadium list inner joined with the country table where the stadium and country ID matches and the name of the country is 

SELECT Club.ClubName, AVG(Player.Age) AS AverageAge FROM Player JOIN Club ON Player.ClubID = Club.ClubID GROUP BY Club.ClubName ORDER BY AverageAge DESC;
-- Select all clubnames, calculate the player age average per club and rename it as # from the player table. Then inner join with club where player and club id matches, order result
-- By average age in a descending order 