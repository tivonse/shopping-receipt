# **shopping-receipt**

## Project Description
This program is created to **print a receipt** for a shopping card including sales tax.

### Conditions
* **Sales tax = roundup(price * quantity * sales tax rate)**
* Sales tax amount should be rounded up to the nearest 0.05 (e.g. 1.13->1.15, 1.16->1.20, 1.151->1.20)
* Use parameters as input or read input value from file

### Input
The input of this program is read from a **JSON** file named e.g. `test_use_case_X.json`. User can modify the input file<br> 
by modifying the properties value `filePath` from `src/main/resources/config/config.properties` file.

### Output
Please be alert that the result, _receipt_, will be generated to the classpath named `output.txt` as default. If you <br>
perform twice or more times of running the application, the text on receipt will be appended at the end of the file every time.

### Tax rates
Location | Sale tax rate   | Food tax rate | Clothing tax rate
------------ | ------------- | ------------- | -------------
CY | 9.75% | exempted | exempted
NA | 8.875% | exempted | exempted

## Implementation
The program is mainly chained with three service classes
* `FileInputService` : used to read the values from source
* `TaxService` - used to calculate the amount of subtotal, tax and total
* `ReceiptPrintingService` : used to print the result to an outputed file
