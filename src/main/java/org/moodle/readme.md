### HU #1 
## diferences beetwen JAVA 8 and java 17/18

The shift from Java 8 to Java 17/21 represents a fundamental change in how developers think about data and code structure. While Java 8 was about "How we process data" (Functional Programming), Java 17/21 is about "How we model data" (Data-Oriented Programming).

Feature	Java 8 	                                    
- Data Storage	POJO:       Mutable, long code, uses "Setters" to change values.	Record: Immutable, 1-line code, values never change once created.	Audit Trail: Data cannot be tampered with after creation.
- Inheritance	Open: Any class can inherit from another. Hard to control.	Sealed Classes: You "permit" exactly which classes can extend a parent.	Security: Prevents "unauthorized" classes from joining your hierarchy.
- Logic	Switch Statement: Basic, only checks values (integers/strings).	Pattern Matching: Advanced switch that checks types and extracts data.	Clarity: Reduces if-else chains and prevents "Missing Case" errors.
- Text Handling	Concatenation (+ "\n") which is messy for SQL or JSON.	Text Blocks ("""): Clean, multi-line strings that look like the output.	Readability: Your code looks like the data it produces.

## Comparison between class and record
- Code Length	40-50 lines (Requires manual Getters, Setters, Constructor, toString, equals).	1 line (Everything is generated automatically by the compiler).
- Boilerplate	High (Lots of repetitive code).	Zero (Clean and minimal).
- Mutability	Mutable: You can change values anytime using setters.	Immutable: Values are fixed (read-only) once created.
- Syntax	public class User { private String name... }	public record User(String name) {}

## Explain
- "NullPointerException" = generate error
- En Java 17/21, the descriptive message:
  "Cannot invoke String.toUpperCase() because 'nullString' is null"

- The '==' operator compares the REFERENCE in the Heap memory.
- Even though they have the same data, they are two distinct objects in different memory locations.
- To compare the CONTENT in Records, we would use company.equals(company2) -> true. ##


### UH #2

- Fall-through (java 8), High risk of "Fall-through" bugs if 'break' is missing, Highly verbose; requires manual assignment to a variable.
- (java 17/2021), "Switch Expressions" are exhaustive and safe, No 'break' needed code, can return a value directly to a variable.
- var Declaration The compiler "guesses" the type from the value, Location	Used anywhere (fields, parameters, etc.), Readability	High for complex return types. Explicit tipping java 8 You must know and write the type, High for r, ducing boilerplate/noise, Only for local variables inside methods.
  	
- Do/while is a bucle 

## loss of precision
When explicitly casting 'double' to 'int', Java removes the fractional part (mantissa) without rounding.
This results in a significant loss of data for financial or academic reports, as 4.9 and 4.0 would both be reported as 4.

## LTS ANALYSIS: Evolution of Exception Diagnostics
In Java 8: Error messages were often generic (like NullPointerException). The developer had to guess which part of a line like 'a.getB().getC()' was null. In Java 17/21: "Helpful NullPointerExceptions" and "Enhanced Error Messages" were introduced. Now, Java explicitly tells you exactly which variable caused the error and provides more context in the StackTrace, reducing debugging time significantly.

### UH #3
## SECURITY AND DESIGN ANALYSIS (JAVA 11):
These collections created with List.of() and Map.of() are IMMUTABLE.
1. Security: They are thread-safe in multithreaded environments because their state does not change.
2. Integrity: They prevent accidental errors when attempting to add a location or technology that does not exist using .add().
If you try to use .add() or .remove(), Java will throw an UnsupportedOperationException.
Performance: Unlike traditional ArrayLists, they occupy less memory because they have a predefined fixed size.