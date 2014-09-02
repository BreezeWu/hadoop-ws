// ----------------------------------------------------------------------------
// 8    XML Processing
// 8.1    Data Model
// 8.2    Schema Validation
// 8.3    Sequence Matching
// 8.4    XML Queries through For Comprehension

// ----------------------------------------------------------------------------
// 8.1    Data Model

// Scala’s data model for XML is an immutable representation of an ordered unranked tree.  In such a tree each node has a label, a sequence of children nodes, and a map from attribute keys to attribute values.

// XML syntax can be used directly in a Scala program, e.g., in value definitions.
val labPhoneBook =
<phonebook>
<descr>Phone numbers of<b>XML</b> hackers.</descr>
<entry>
<name>Burak</name>
<phone where="work"> +41 21 693 68 67 </phone>
<phone where="mobile"> +41 78 601 54 36 </phone>
</entry>
</phonebook>;

labPhoneBook.\("descr")
labPhoneBook.\("entry")
// ----------------------------------------------------------------------------
// 8.2    Schema Validation

// ----------------------------------------------------------------------------
// 8.3    Sequence Matching

// The following example shows how to add an entry to a phonebook element.
import scala.xml.Node ;
def add(phonebook: Node, newEntry: Node): Node =
    phonebook match {
        case <phonebook>{ cs @ _* }</phonebook> =>     <phonebook>{ cs }{ newEntry }</phonebook>
    }
val newPhoneBook =
    add(scala.xml.XML.loadFile("savedPhoneBook"),     <entry>
                        <name>Sebastian</name>
                        <phone where="work">+41 21 693 68 67</phone>
                        </entry>);
// ----------------------------------------------------------------------------
// 8.4    XML Queries through For Comprehension
