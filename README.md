# DailyTradeReportingEngine
DailyTradeReportingEngine


Problem Statement :

A Daily Trade Reporting Engine for incoming instructions. Its input is a set of instructions and its output is a report printed in console with below attributes.
==> Amount in USD settled incoming everyday
==> Amount in USD settled outgoing everyday
==> Ranking of entities based on incoming and outgoing amount. Eg: If entity hsbc instructs the highest amount for a buy instruction, then hsbc is rank 1 for outgoing

Sample Input Data from CSV:

Entity,Buy/Sell,AgreedFx,Currency,InstructionDate,SettlementDate,Units,Price per unit

hsbc,B,0.50,SGP,2017-01-01,2017-01-01,200,100.25

hsbc,B,0.50,SGP,2017-01-01,2017-01-01,200,100

citi,S,0.22,AED,2017-01-01,2017-01-07,450,150.50

lloyds,S,0.22,AED,2017-01-01,2017-01-11,450,120.50

lloyds,S,0.20,AED,2017-01-01,2017-01-11,450,120

hdfc,B,0.22,AED,2017-01-01,2017-01-14,450,150.50

icici,S,0.22,AED,2017-01-01,2017-03-17,450,100.50

icici,S,0.20,AED,2017-01-01,2017-01-02,150,100

icici,B,0.20,INR,2017-01-01,2017-01-02,150,100

Sample Output Data in console:

USD Settled Incoming(Sell) Report everyday :

{2017-01-11=22729.5, 2017-01-02=3000.0, 2017-01-08=14899.5, 2017-03-19=9949.5}

USD Settled Outgoing(Buy) Report everyday :

{2017-01-15=14899.5, 2017-01-02=23025.0}

Ranking Report
{Incoming Rankings:={hsbc=20025.0, hdfc=14899.5, icici=3000.0}, Outgoing Rankings:={lloyds=22729.5, citi=14899.5, icici=12949.5}}
