# tracom
Decision on choosing way of saving data

I created five tables to save data i.e countries ,bank branches ,clients ,devices and transactions table with related data in each table.
1.This helped to reduce data- redundancy  for example in countries we have countryCode column which is unique and is used to represent each country.So instead of users  inserting any country while assigning the pos device , users will save countries then select the country hence reducing data-redundancy.

2.Only related data are stored in each table .This prevents any issues stemming from database modifications such as insertions, deletions, and updates.

3.Reporting becomes easy.You can easily give a report on each table for example bank branches because you will find them in the same table.

4.Data is well organized and give you an easy time to retrieve

Transaction tables is used to store information on devices being assigned to clients which contains country code to store the country assigned,client code to store the client assigned ,device serial number to store the device assigned and branch to store the bank branches .This help to get multi tenancy since the same API can be used for different countries and store all the information.it's easy to retrieve information since you can filter with countries,bank branches ,devices and clients.
Validation is done to make sure that only registered codes for branches ,countries,clients and registered serial number for devices are accepted before saving a transactions.






Devices
Saving a device
Url :http://localhost:8081/devices
RequestMethod:POST
Body accepts application json
{
	"modifiedBy":"KAA",
	"modelType":"KENYA",
	"bankBranch":"dd",
	"country":"NAkURU",
	"serialNumber":"P007"
	
}
updating a device
Url :http://localhost:8081/devices
RequestMethod:PUT
Body accepts application json
{
          “id”:1,
	"modifiedBy":"KAA",
	"modelType":"KENYA",
	"bankBranch":"dd",
	"country":"NAkURU",
	"serialNumber":"P007"
	
}

Deleting a device
Url :http://localhost:8081/devices/id
RequestMethod:DELETE

view a device
Url :http://localhost:8081/devices/id
RequestMethod:GET

view all devices
Url :http://localhost:8081/devices
RequestMethod:GET



Clients
Saving a client
Url :http://localhost:8081/client
RequestMethod:POST
Body accepts application json
{  
	"clientCode":"ME",
	"clientName":"fff",
	"modifiedBy":"fred"
}

updating a client
Url :http://localhost:8081/client
RequestMethod:PUT
Body accepts application json
{         
          “id”:1,
	"clientCode":"ME",
	"clientName":"fff",
	"modifiedBy":"fred"
}

	
Deleting a client
Url :http://localhost:8081/client/id
RequestMethod:DELETE

view a client
Url :http://localhost:8081/client/id
RequestMethod:GET

view all clients
Url :http://localhost:8081/client
RequestMethod:GET

Branches
Saving a client
Url :http://localhost:8081/branches
RequestMethod:POST
Body accepts application json
{  
		
	"branchCode":"EQY",
	"branchName":"ff",
	"modifiedBy":"ffffff"

}

updating a branch
Url ::http://localhost:8081/branches
RequestMethod:PUT
Body accepts application json
{         
      	"id":1,
	"branchCode":"EQY",
	"branchName":"ff",
	"modifiedBy":"ffffff"

}

	

Deleting a branch
Url :http://localhost:8081/branches/id
RequestMethod:DELETE

view a branch
Url :http://localhost:8081/branches/id
RequestMethod:GET

view all branches
Url :http://localhost:8081/branches
RequestMethod:GET


Transactions
Saving a transaction
Url :http://localhost:8081/transactions
RequestMethod:POST
Body accepts application json
{
	"deviceSerialnumber":"asjkash",
	"bankBranch":"EQY",
	"clientCode":"ME",
	"countryCode":"KE",
	"modifiedBy":"OTI"
}








updating a transaction
Url :http://localhost:8081/transactions
RequestMethod:PUT
Body accepts application json
{         "id":1,
	"deviceSerialnumber":"asjkash",
	"bankBranch":"EQY",
	"clientCode":"ME",
	"countryCode":"KE",
	"modifiedBy":"OTI"

}




	

Deleting a transaction
Url :http://localhost:8081/transactions/id
RequestMethod:DELETE

view a transaction
Url :http://localhost:8081/transactions/id
RequestMethod:GET

view all transactions
Url :http://localhost:8081/transactios
RequestMethod:GET


Countries
Saving a country
Url :http://localhost:8081/countries
RequestMethod:POST
Body accepts application json
{
	"countryCode":"KE",
	"countryName":"TAN",
	"modifiedBy":"OTI"
}


updating a country
Url ::http://localhost:8081/countries
RequestMethod:PUT
Body accepts application json
{"id":3,
	"countryCode":"KE",
	"countryName":"TAN",
	"modifiedBy":"OTI"
}




	

Deleting a country
Url :http://localhost:8081/countries/id
RequestMethod:DELETE

view a country
Url :http://localhost:8081/countries/id
RequestMethod:GET

view all branches
Url :http://localhost:8081/countries
RequestMethod:GET



