import java.util.*;

public class SymbolTable {
	
	Map<String, Set<SymbolType>> sym_table;
	public String scope_name;
	
	public SymbolTable() {
		sym_table = new HashMap<>();
		SymbolTableVisitor.localScopes.put(this, null);	// o pateras tha dixnei se null
	}
	
	public SymbolTable(String scope_name) {
		sym_table = new HashMap<>();
		this.scope_name = scope_name;
	}
	
	SymbolTable enterScope(String new_scope_name){
		
		SymbolTable new_table = new SymbolTable(new_scope_name);	// new kid symbol table
		SymbolTableVisitor.localScopes.put(new_table, this);		// vazw to new table na dixnei ston patera
		
		return new_table;
	}
	
	boolean insert(SymbolType type){
		if (sym_table.containsKey(scope_name)){				// uparxei to key
			
			Set<SymbolType> syms= sym_table.get(scope_name);
			
			for (SymbolType t: syms){
				if (t.name.equals(type.name) && t.kind.equals(type.kind)){
					System.out.println("Variable name already exists at " + scope_name + " scope");
					System.out.println("Redeclaration");	
					return false;
				}
			}
			
			sym_table.get(scope_name).add(type);				// ara vale sto uparxon value to new element
		}
		else {
			Set<SymbolType> val = new HashSet<SymbolType>();	// den uparxei
			val.add(type);										// ara ftiakse new kai vale ekei tin timi
			
			sym_table.put(scope_name,val);
		}
		
		return true;
	}	
	
//	boolean lookup(String name){
//		
//		SymbolTable temp = this;
//		
//		while (temp != null){
//			Set<SymbolType> syms = temp.sym_table.get(temp.scopeName);
//			
//			for (SymbolType type: syms){
//				if (type.name.equals(name)){
//					System.out.println("Found it in " + temp.scopeName + " scope");
//					return true;
//				}
//			}
//			System.out.println("DID NOT Found it in " + temp.scopeName + " scope");
//			temp = temp.parent;
//		}
//		
//		return false;
//	}
//	
	SymbolTable exitScope(){
		// return the scope that the current scope points to
		SymbolTable t = SymbolTableVisitor.localScopes.get(this);
		return t;
	}
	
	void printSymbolTable(){
	      
	      for (Map.Entry<String, Set<SymbolType>> entry : sym_table.entrySet()) {
	    	    String key = entry.getKey();
	    	    Set<SymbolType> val = entry.getValue();
	    	    
	    	    System.out.println("------------------------");
	    	    System.out.println("Scope: " + key);
	    	    System.out.println("------------------------");
	    	    
	    	    for (SymbolType type : val){
	    	    	type.printType();
	    	    }
	    	    System.out.println("=========================");
	    	}
	}
	
}
