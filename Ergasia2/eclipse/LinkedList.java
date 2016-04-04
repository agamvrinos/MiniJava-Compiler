class LinkedList{
    public static void main(String[] a){
    	System.out.println(new LL().Start());
    }
}

class Element {
    int Age ;
    int Salary ;
    boolean Married ;

    // Initialize some class variables
    public boolean Init(int v_Age, int v_Salary, boolean v_Married){
		Age = v_Age ;
		Salary = v_Salary ;
		Married = v_Married ;
		return true ;
    }

    public int GetAge(){
    	return Age ;
    }

    public int GetSalary(){
    	return Salary ;
    }

    public boolean GetMarried(){
    	return Married ;
    }

    // This method returns true if the object "other"
    // has the same values for age, salary and
    public boolean Equal(Element other){
		boolean ret_val ;
		int aux01 ;
		int aux02 ;
		int nt ;
		ret_val = true ;
	
		aux01 = other.GetAge();
		if (!(this.Compare(aux01,Age))) ret_val = false ;
		else {
		    aux02 = other.GetSalary();
		    if (!(this.Compare(aux02,Salary))) ret_val = false ;
		    else
			if (Married)
			    if (!(other.GetMarried())) ret_val = false;
			    else nt = 0 ;
			else
			    if (other.GetMarried()) ret_val = false;
			    else nt = 0 ;
		}
	
		return ret_val ;
    }

    // This method compares two integers and
    // returns true if they are equal and false
    // otherwise
    public boolean Compare(int num1 , int num2){
		boolean retval ;
		int aux02 ;
		retval = false ;
		aux02 = num2 + 1 ;
		if (num1 < num2) retval = false ;
		else if (!(num1 < aux02)) retval = false ;
		else retval = true ;
		return retval ;
    }

}

class List{
    Element elem ;
    List next ;
    boolean end ;

    // Initialize the node list as the last node
    public boolean Init(){
		end = true ;
		return true ;
    }


    public boolean GetEnd(){
		return end ;
    }

    public Element GetElem(){
		return elem ;
    }

    public List GetNext(){
		return next ;
    }


}


// this class invokes the methods to insert, delete,
// search and print the linked list
class LL{

    public int Start(){

	List head ;
	List last_elem ;
	boolean aux01 ;
	Element el01 ;
	Element el02 ;
	Element el03 ;


	return 0 ;

    }

}
