package sg.iv.flipkart.july_2015.machine_round.skipped;

//12 -Given a string regex and another string pat find whether the pattern is acceptable against given regex string.
//Regex string contains following characters and special characters:
//Normal alphabets  a to z and A to Z
//  $   all string should end with all characters preceding $
//Example:
//Regex :abc$ ,
//Pattern: abcd(Not acceptable) , abc(acceptable), ab(Not acceptable), dhfusdhabc(acceptable) etc..
//  ^   all string should start with all characters exceeding ^
//Example: Regex : ^abc
//Pattern: abcd(acceptable) , abc(acceptable), ab(Not acceptable), dhfusdhabc(NOT acceptable) etc..
//Regex: ^ then only pattern acceptable is null.
//  .   any character can be mapped to dot except null
//Example 1: Regex : .abc
//Pattern: Zabc(acceptable) , abc(NOT acceptable), ab(Not acceptable), habc(acceptable) etc..
//Example 2: Regex :a.bc
//Pattern: abc(NOT acceptable) , aXbc(acceptable), ab(Not acceptable), habc(NOT acceptable) etc..
//  * -the character just preceding * can be repeated n time where (n>=0)
//Example 1: Regex :abc*de
//Pattern: abccccccccccde(acceptable), abcde(acceptable), abcccd(not acceptable)
//Code should follow OOPs principle such as modularity (make each function for each special character), encapsulation etc.

public class Problem12_skipped {

}

class RegexCheck {
	
}