import syntaxtree.AllocationExpression;
import syntaxtree.AndExpression;
import syntaxtree.ArrayAllocationExpression;
import syntaxtree.ArrayAssignmentStatement;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.ArrayType;
import syntaxtree.AssignmentStatement;
import syntaxtree.Block;
import syntaxtree.BooleanType;
import syntaxtree.BracketExpression;
import syntaxtree.ClassDeclaration;
import syntaxtree.ClassExtendsDeclaration;
import syntaxtree.Clause;
import syntaxtree.CompareExpression;
import syntaxtree.Expression;
import syntaxtree.ExpressionList;
import syntaxtree.ExpressionTail;
import syntaxtree.ExpressionTerm;
import syntaxtree.FalseLiteral;
import syntaxtree.FormalParameter;
import syntaxtree.FormalParameterList;
import syntaxtree.FormalParameterTail;
import syntaxtree.FormalParameterTerm;
import syntaxtree.Goal;
import syntaxtree.Identifier;
import syntaxtree.IfStatement;
import syntaxtree.IntegerLiteral;
import syntaxtree.IntegerType;
import syntaxtree.MainClass;
import syntaxtree.MessageSend;
import syntaxtree.MethodDeclaration;
import syntaxtree.MinusExpression;
import syntaxtree.NotExpression;
import syntaxtree.PlusExpression;
import syntaxtree.PrimaryExpression;
import syntaxtree.PrintStatement;
import syntaxtree.Statement;
import syntaxtree.ThisExpression;
import syntaxtree.TimesExpression;
import syntaxtree.TrueLiteral;
import syntaxtree.Type;
import syntaxtree.TypeDeclaration;
import syntaxtree.VarDeclaration;
import syntaxtree.WhileStatement;
import visitor.GJDepthFirst;
import java.util.*;

public class TypeCheckVisitor extends GJDepthFirst<String, String>{
		
		String current_class;
		String current_method;
		List<String> parameters_check;
		
		public TypeCheckVisitor() {
			parameters_check = new ArrayList<>();
		}
		
		void printGlobalScopes(){
			System.out.println("*****************************");
			System.out.println("Printing Global Scopes");
			System.out.println("*****************************");
			for (Map.Entry<String, SymbolTable> entry : Main.globalScope.entrySet()) {
	    	    String key = entry.getKey();
	    	    SymbolTable s = entry.getValue();
	    	    String val = "";
	    	    if (s != null)
	    	    	val = s.scope_name;
	    	    System.out.println("Key: " + key);
    	    	System.out.println("Value: " + ((s == null) ? "null" : val));
	    	    System.out.println("------------------------");
	    	}
		}
		
		void printLocalScopes(){
			
			System.out.println("*****************************");
			System.out.println("Printing Local Scopes");
			System.out.println("*****************************");
			for (Map.Entry<SymbolTable, SymbolTable> entry : Main.localScopes.entrySet()) {
	    	    String key = entry.getKey().scope_name;
	    	    SymbolTable s = entry.getValue();
	    	    String val = "";
	    	    if (s != null)
	    	    	val = s.scope_name;
	    	    System.out.println("Key: " + key);
    	    	System.out.println("Value: " + ((s == null) ? "null" : val));
	    	    System.out.println("------------------------");
	    	}
		}
		
		void printAllSymbolTables(){
			
			System.out.println("*****************************");
			System.out.println("Printing Symbol Tables");
			System.out.println("*****************************");
			for (Map.Entry<SymbolTable, SymbolTable> entry : Main.localScopes.entrySet()) {
				SymbolTable key = entry.getKey();
				key.printSymbolTable();
	    	    
	    	}
		}
		
		/**
		    * f0 -> MainClass()
		    * f1 -> ( TypeDeclaration() )*
		    * f2 -> <EOF>
		    */
		   public String visit(Goal n, String argu) {
		      String _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      return _ret;
		   }
		   
		   /**
		    * f0 -> "class"
		    * f1 -> Identifier()
		    * f2 -> "{"
		    * f3 -> "public"
		    * f4 -> "static"
		    * f5 -> "void"
		    * f6 -> "main"
		    * f7 -> "("
		    * f8 -> "String"
		    * f9 -> "["
		    * f10 -> "]"
		    * f11 -> Identifier()
		    * f12 -> ")"
		    * f13 -> "{"
		    * f14 -> ( VarDeclaration() )*
		    * f15 -> ( Statement() )*
		    * f16 -> "}"
		    * f17 -> "}"
		    */
		   public String visit(MainClass n, String argu) {
		      String _ret=null;
		      n.f0.accept(this, argu);
		      String className = n.f1.accept(this, argu);
		      current_class = className;
		      current_method = "main";
		      n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      n.f5.accept(this, argu);
		      n.f6.accept(this, argu);
		      n.f7.accept(this, argu);
		      n.f8.accept(this, argu);
		      n.f9.accept(this, argu);
		      n.f10.accept(this, argu);
		      n.f11.accept(this, argu);
		      n.f12.accept(this, argu);
		      n.f13.accept(this, argu);
		      n.f14.accept(this, argu);
		      n.f15.accept(this, argu);
		      n.f16.accept(this, argu);
		      n.f17.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> ClassDeclaration()
		    *       | ClassExtendsDeclaration()
		    */
		   public String visit(TypeDeclaration n, String argu) {
		      return n.f0.accept(this, argu);
		   }

		   /**
		    * f0 -> "class"
		    * f1 -> Identifier()
		    * f2 -> "{"
		    * f3 -> ( VarDeclaration() )*
		    * f4 -> ( MethodDeclaration() )*
		    * f5 -> "}"
		    */
		   public String visit(ClassDeclaration n, String argu) {
		      String _ret=null;
		      n.f0.accept(this, argu);
		      String className = n.f1.accept(this, argu);
		      current_class = className;
		      n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      n.f5.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> "class"
		    * f1 -> Identifier()
		    * f2 -> "extends"
		    * f3 -> Identifier()
		    * f4 -> "{"
		    * f5 -> ( VarDeclaration() )*
		    * f6 -> ( MethodDeclaration() )*
		    * f7 -> "}"
		    */
		   public String visit(ClassExtendsDeclaration n, String argu) {
		      String _ret=null;
		      n.f0.accept(this, argu);
		      String className = n.f1.accept(this, argu);
		      current_class = className;
		      n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      n.f5.accept(this, argu);
		      n.f6.accept(this, argu);
		      n.f7.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> Type()
		    * f1 -> Identifier()
		    * f2 -> ";"
		    */
		   public String visit(VarDeclaration n, String argu) {
		      String _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> "public"
		    * f1 -> Type()
		    * f2 -> Identifier()
		    * f3 -> "("
		    * f4 -> ( FormalParameterList() )?
		    * f5 -> ")"
		    * f6 -> "{"
		    * f7 -> ( VarDeclaration() )*
		    * f8 -> ( Statement() )*
		    * f9 -> "return"
		    * f10 -> Expression()
		    * f11 -> ";"
		    * f12 -> "}"
		    */
		   public String visit(MethodDeclaration n, String argu) {
		      String _ret=null;
		      n.f0.accept(this, argu);
		      String methodType = n.f1.accept(this, argu);	// className, int, int[], boolean
		      String methodName = n.f2.accept(this, argu);
		      current_method = methodName;
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      n.f5.accept(this, argu);
		      n.f6.accept(this, argu);
		      n.f7.accept(this, argu);
		      n.f8.accept(this, argu);
		      n.f9.accept(this, argu);
		      String return_type = n.f10.accept(this, argu);
		      n.f11.accept(this, argu);
		      n.f12.accept(this, argu);
		      
		      
		      return _ret;
		   }

		   /**
		    * f0 -> FormalParameter()
		    * f1 -> FormalParameterTail()
		    */
		   public String visit(FormalParameterList n, String argu) {
		      String _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> Type()
		    * f1 -> Identifier()
		    */
		   public String visit(FormalParameter n, String argu) {
		      String _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> ( FormalParameterTerm() )*
		    */
		   public String visit(FormalParameterTail n, String argu) {
		      return n.f0.accept(this, argu);
		   }

		   /**
		    * f0 -> ","
		    * f1 -> FormalParameter()
		    */
		   public String visit(FormalParameterTerm n, String argu) {
		      String _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> ArrayType()
		    *       | BooleanType()
		    *       | IntegerType()
		    *       | Identifier()
		    */
		   public String visit(Type n, String argu) {
		      return n.f0.accept(this, argu);
		   }

		   /**
		    * f0 -> "int"
		    * f1 -> "["
		    * f2 -> "]"
		    */
		   public String visit(ArrayType n, String argu) {
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      return "int[]";
		   }

		   /**
		    * f0 -> "boolean"
		    */
		   public String visit(BooleanType n, String argu) {
		      n.f0.accept(this, argu);
		      return "boolean";
		   }

		   /**
		    * f0 -> "int"
		    */
		   public String visit(IntegerType n, String argu) {
		      n.f0.accept(this, argu);
		      return "int";
		   }

		   /**
		    * f0 -> Block()
		    *       | AssignmentStatement()	//DONE
		    *       | ArrayAssignmentStatement()
		    *       | IfStatement()			//DONE
		    *       | WhileStatement()		//DONE
		    *       | PrintStatement()		//DONE
		    */
		   public String visit(Statement n, String argu) {
		      return n.f0.accept(this, argu);
		   }

		   /**
		    * f0 -> "{"
		    * f1 -> ( Statement() )*
		    * f2 -> "}"
		    */
		   public String visit(Block n, String argu) {
		      String _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> Identifier()
		    * f1 -> "="
		    * f2 -> Expression()
		    * f3 -> ";"
		    */
		   public String visit(AssignmentStatement n, String argu) {
			   
		      String lvalue = n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      String rvalue = n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      
		      String ltype = Main.mapping.get(current_class).get(current_method).typeCheck(lvalue, "variable");
		      String rtype = Main.mapping.get(current_class).get(current_method).typeCheck(rvalue, "variable");
		      
		      if (ltype == null)
		    	  throw new RuntimeException("Type before = doesn't exist");
		      if (rtype == null)
		    	  throw new RuntimeException("Error at expr after = ");
		      
//		      if (!ltype.equals(rtype)){	// an den einai isa check gia subtype
//		    	  SymbolTable mother = Main.localScopes.get(Main.globalScope.get(rtype));
//		    	  if (mother != null){
//		    		  if (!mother.scope_name.equals(ltype))
//		    			  throw new RuntimeException("Before and after = types must be the same");
//		    	  }
//		    	  else
//		    		  throw new RuntimeException("Type mismatch: cannot convert from " + rtype + " to " + ltype);
//		      }
		      
		      boolean found = false;
		      if (!ltype.equals(rtype)){	// an den einai isa check gia subtype
		    	  SymbolTable mother = Main.localScopes.get(Main.globalScope.get(rtype));
		    	  while (mother != null){	// oso den exw ftasei mexri terma panw
		    		  if (mother.scope_name.equals(ltype)){
		    			  found = true;
		    			  break;
		    		  }
		    		  else
		    			  mother = Main.localScopes.get(Main.globalScope.get(mother.scope_name));
		    	  }
		    	  if (!found)
		    		  throw new RuntimeException("Type mismatch: cannot convert from " + rtype + " to " + ltype);
		      }
		      
		      
		      
		      return ltype;
		   }

		   /**
		    * f0 -> Identifier()
		    * f1 -> "["
		    * f2 -> Expression()
		    * f3 -> "]"
		    * f4 -> "="
		    * f5 -> Expression()
		    * f6 -> ";"
		    */
		   public String visit(ArrayAssignmentStatement n, String argu) {
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      n.f5.accept(this, argu);
		      n.f6.accept(this, argu);
		      return null;
		   }

		   /**
		    * f0 -> "if"
		    * f1 -> "("
		    * f2 -> Expression()
		    * f3 -> ")"
		    * f4 -> Statement()
		    * f5 -> "else"
		    * f6 -> Statement()
		    */
		   public String visit(IfStatement n, String argu) {
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      String expr = n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      n.f5.accept(this, argu);
		      n.f6.accept(this, argu);
		      
		      String expr_type = Main.mapping.get(current_class).get(current_method).typeCheck(expr, "variable");
		      
		      if (expr_type != null){
			      if (!expr_type.equals("boolean") ){
			    	  throw new RuntimeException("Condition of if statement must be of type boolean");
			      }
		      }
		      else 
		    	  throw new RuntimeException("Type at condition of if statement does not exist");
		      
		      return null;
		   }

		   /**
		    * f0 -> "while"
		    * f1 -> "("
		    * f2 -> Expression()
		    * f3 -> ")"
		    * f4 -> Statement()
		    */
		   public String visit(WhileStatement n, String argu) {
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      String expr = n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      
		      String expr_type = Main.mapping.get(current_class).get(current_method).typeCheck(expr, "variable");
		      
		      if (expr_type != null){
			      if (!expr_type.equals("boolean") ){
			    	  throw new RuntimeException("Condition of while statement must be of type boolean");
			      }
		      }
		      else 
		    	  throw new RuntimeException("Type at condition of while statement does not exist");
		      
		      return null;
		   }

		   /**
		    * f0 -> "System.out.println"
		    * f1 -> "("
		    * f2 -> Expression()
		    * f3 -> ")"
		    * f4 -> ";"
		    */
		   public String visit(PrintStatement n, String argu) {
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      String value = n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      
		      String ltype = Main.mapping.get(current_class).get(current_method).typeCheck(value, "variable");
		      
		      if (ltype != null){
			      if (!ltype.equals("int"))
			    	  throw new RuntimeException("Print statement accepts only int types");
		      }
		      else
		    	  throw new RuntimeException("Error: Type does not exist!");
		      
		      return ltype;
		   }

		   /**	
		    * f0 -> AndExpression()			// DONE
		    *       | CompareExpression()	// DONE
		    *       | PlusExpression()		// DONE
		    *       | MinusExpression()		// DONE
		    *       | TimesExpression()		// DONE
		    *       | ArrayLookup()			// DONE
		    *       | ArrayLength()			// DONE
		    *       | MessageSend()			// DONE
		    *       | Clause()				// DONE
		    */
		   public String visit(Expression n, String argu) {
		      return n.f0.accept(this, argu);
		   }

		   /**
		    * f0 -> Clause()
		    * f1 -> "&&"
		    * f2 -> Clause()
		    */
		   public String visit(AndExpression n, String argu) {
		      
		      String lvalue = n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      String rvalue = n.f2.accept(this, argu);
		      
		      String ltype = Main.mapping.get(current_class).get(current_method).typeCheck(lvalue, "variable");
		      String rtype = Main.mapping.get(current_class).get(current_method).typeCheck(rvalue, "variable");
		      
		      if (ltype == null)
		    	  throw new RuntimeException("Type before && doesn't exist");
		      if (rtype == null)
		    	  throw new RuntimeException("Type after && doesn't exist");
		      
		      if (!ltype.equals("boolean"))
		    	  throw new RuntimeException("before && type must be boolean");
		      if (!rtype.equals("boolean"))
		    	  throw new RuntimeException("after && type must be boolean");
		      
		      return "boolean";
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "<"
		    * f2 -> PrimaryExpression()
		    */
		   public String visit(CompareExpression n, String argu) {
			  String lvalue = n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      String rvalue = n.f2.accept(this, argu);
		      
		      String ltype = Main.mapping.get(current_class).get(current_method).typeCheck(lvalue, "variable");
		      String rtype = Main.mapping.get(current_class).get(current_method).typeCheck(rvalue, "variable");
		      
		      if (ltype == null)
		    	  throw new RuntimeException("Type before < doesn't exist");
		      if (rtype == null)
		    	  throw new RuntimeException("Type after < doesn't exist");
		      
		      if (!ltype.equals("int"))
		    	  throw new RuntimeException("before < type must be integer");
		      if (!rtype.equals("int"))
		    	  throw new RuntimeException("after < type must be integer");
		      
		      return "boolean";
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "+"
		    * f2 -> PrimaryExpression()
		    */
		   public String visit(PlusExpression n, String argu) {
		      String lvalue = n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      String rvalue = n.f2.accept(this, argu);
		      
		      String ltype = Main.mapping.get(current_class).get(current_method).typeCheck(lvalue, "variable");
		      String rtype = Main.mapping.get(current_class).get(current_method).typeCheck(rvalue, "variable");
		      
		      if (ltype == null)
		    	  throw new RuntimeException("Type before + doesn't exist");
		      if (rtype == null)
		    	  throw new RuntimeException("Type after + doesn't exist");
		      
		      if (!ltype.equals("int"))
		    	  throw new RuntimeException("before + type must be integer");
		      if (!rtype.equals("int"))
		    	  throw new RuntimeException("after + type must be integer");
		      
		      return ltype;
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "-"
		    * f2 -> PrimaryExpression()
		    */
		   public String visit(MinusExpression n, String argu) {
			  String lvalue = n.f0.accept(this, argu);
			  n.f1.accept(this, argu);
			  String rvalue = n.f2.accept(this, argu);
			  
			  String ltype = Main.mapping.get(current_class).get(current_method).typeCheck(lvalue, "variable");
			  String rtype = Main.mapping.get(current_class).get(current_method).typeCheck(rvalue, "variable");
			  
			  if (ltype == null)
				  throw new RuntimeException("Type before - doesn't exist");
			  if (rtype == null)
				  throw new RuntimeException("Type after - doesn't exist");
			  
			  if (!ltype.equals("int"))
				  throw new RuntimeException("before - type must be integer");
			  if (!rtype.equals("int"))
				  throw new RuntimeException("after - type must be integer");
			  
			  return ltype;
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "*"
		    * f2 -> PrimaryExpression()
		    */
		   public String visit(TimesExpression n, String argu) {
			  String lvalue = n.f0.accept(this, argu);
			  n.f1.accept(this, argu);
			  String rvalue = n.f2.accept(this, argu);
			      
			  String ltype = Main.mapping.get(current_class).get(current_method).typeCheck(lvalue, "variable");
			  String rtype = Main.mapping.get(current_class).get(current_method).typeCheck(rvalue, "variable");
				  
			  if (ltype == null)
				  throw new RuntimeException("Type before * doesn't exist");
			  if (rtype == null)
				  throw new RuntimeException("Type after * doesn't exist");
			  
			  if (!ltype.equals("int"))
				  throw new RuntimeException("before * type must be integer");
			  if (!rtype.equals("int"))
				  throw new RuntimeException("after * type must be integer");
			  
			  return ltype;
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "["
		    * f2 -> PrimaryExpression()
		    * f3 -> "]"
		    */
		   public String visit(ArrayLookup n, String argu) {
			   
		      String lvalue = n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      String rvalue = n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      
		      String ltype = Main.mapping.get(current_class).get(current_method).typeCheck(lvalue, "variable");
		      String rtype = Main.mapping.get(current_class).get(current_method).typeCheck(rvalue, "variable");
		      
		      if (ltype == null || rtype == null)
		    	  throw new RuntimeException("Error at array lookup expression");
		    		  
		      if (!ltype.equals("int[]"))
		    	  throw new RuntimeException("Before [ must be of type int array");
		      if (!rtype.equals("int"))
		    	  throw new RuntimeException("[x]: x must be of type int");
		      
		      return rtype;
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "."
		    * f2 -> "length"
		    */
		   public String visit(ArrayLength n, String argu) {
			   
		      String lvalue = n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      
		      String ltype = Main.mapping.get(current_class).get(current_method).typeCheck(lvalue, "variable");
		      
		      if (ltype == null)
		    	  throw new RuntimeException("Error at array.length expression");
		      if (!ltype.equals("int[]"))
		    	  throw new RuntimeException("Before . must be of type int array");
		      
		      return "int";
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "."
		    * f2 -> Identifier()
		    * f3 -> "("
		    * f4 -> ( ExpressionList() )?
		    * f5 -> ")"
		    */
		   public String visit(MessageSend n, String argu) {
			  //TODO: to f0 prepei na einai "this" h identifier tupou klassis
			  //TODO: to f3 prepei na einai onoma sunartisis i opoia uparxei ston current typo klasis i ston patera
			  //TODO: oi tupoi tou f4 prepei na einai idioi me tous tupous pou exei oristei i sunartisi
		      String beforeStop = n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      String methodName = n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      n.f5.accept(this, argu);
		      
		      if (beforeStop.equals("int") || beforeStop.equals("int[]") || beforeStop.equals("boolean"))
		    	  throw new RuntimeException("The left-hand side of an assignment must be a variable");
		      
		      String class_type = Main.mapping.get(current_class).get(current_method).typeCheck(beforeStop, "variable");
		      SymbolType mtype = null;
		      
		      if (class_type == null)
		    	  throw new RuntimeException("Type cannot be resolved");
		      else {
		    	  
		    	  if (class_type.equals("this")){
		    		  mtype = (Main.globalScope.get(current_class)).lookup(methodName, "method");
		    	  }
		    	  else {	// id case
		    		  mtype = (Main.globalScope.get(class_type)).lookup(methodName, "method");
		    	  }
		    	  if (mtype == null){
		    		  throw new RuntimeException("The method " + methodName +  " is undefined for the type " + class_type );
		    	  }
		    	  else {	// prepei na koitaksw an ta orismata subiptoun
		    		  if (mtype.parameters.size() != parameters_check.size()){
		    			  throw new RuntimeException("Wrong number of parameters at method call: " + methodName);
		    		  }
		    		  else {	// equal size -> need to check for same types
		    			  for (int i = 0; i < mtype.parameters.size(); i++){
		    				  if (mtype.parameters.get(i) != parameters_check.get(i)){
		    					  throw new RuntimeException("Wrong parameter type at method call: " + methodName);
		    				  }
		    			  }
		    		  }
		    	  }
		      }
		      
		      parameters_check.clear();		// clear array for next method call
		      return mtype.type;
		   }

		   /**
		    * f0 -> Expression()
		    * f1 -> ExpressionTail()
		    */
		   public String visit(ExpressionList n, String argu) {
		      String par1 = n.f0.accept(this, argu);
		      
		      String par1type = Main.mapping.get(current_class).get(current_method).typeCheck(par1, "variable");
		      if (par1type != null)
		    	  parameters_check.add(par1type);
		      else 
		    	  throw new RuntimeException(par1 + " cannot be resolved to a variable");
		      
		      n.f1.accept(this, argu);
		      
		      for (String i : parameters_check){
		    	  System.out.println(i + " lol");
		      }
	    	  return null;
		   }

		   /**
		    * f0 -> ( ExpressionTerm() )*
		    */
		   public String visit(ExpressionTail n, String argu) {
		      return n.f0.accept(this, argu);
		   }

		   /**
		    * f0 -> ","
		    * f1 -> Expression()
		    */
		   public String visit(ExpressionTerm n, String argu) {
		      n.f0.accept(this, argu);
		      String parameter_type = n.f1.accept(this, argu);
		      
		      String partype = Main.mapping.get(current_class).get(current_method).typeCheck(parameter_type, "variable");
		      if (partype != null)
		    	  parameters_check.add(partype);
		      else
		    	  throw new RuntimeException(parameter_type + " cannot be resolved to a variable");
		      
		      return null;
		   }

		   /**
		    * f0 -> NotExpression()			// DONE
		    *       | PrimaryExpression()	// DONE
		    */
		   public String visit(Clause n, String argu) {
		      return n.f0.accept(this, argu);
		   }

		   /**
		    * f0 -> IntegerLiteral()	// DONE
		    *       | TrueLiteral()		// DONE
		    *       | FalseLiteral()	// DONE
		    *       | Identifier()		// DONE
		    *       | ThisExpression()	// DONE
		    *       | ArrayAllocationExpression()	// DONE
		    *       | AllocationExpression()		// DONE
		    *       | BracketExpression()			// DONE
		    */
		   public String visit(PrimaryExpression n, String argu) {
			   
			  String retval = n.f0.accept(this, argu);
		      return retval;
		   }

		   /**
		    * f0 -> <INTEGER_LITERAL>
		    */
		   public String visit(IntegerLiteral n, String argu) {
		      n.f0.accept(this, argu);
		      return "int";
		   }

		   /**
		    * f0 -> "true"
		    */
		   public String visit(TrueLiteral n, String argu) {
		      n.f0.accept(this, argu);
		      return "boolean";
		   }

		   /**
		    * f0 -> "false"
		    */
		   public String visit(FalseLiteral n, String argu) {
		      n.f0.accept(this, argu);
		      return "boolean";
		   }

		   /**
		    * f0 -> <IDENTIFIER>
		    */
		   public String visit(Identifier n, String argu) {
		      n.f0.accept(this, argu);
		      return n.f0.toString();
		   }

		   /**
		    * f0 -> "this"
		    */
		   public String visit(ThisExpression n, String argu) {
		      n.f0.accept(this, argu);
		      return "this";
		   }

		   /**
		    * f0 -> "new"
		    * f1 -> "int"
		    * f2 -> "["
		    * f3 -> Expression()
		    * f4 -> "]"
		    */
		   public String visit(ArrayAllocationExpression n, String argu) {
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      String lvalue = n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      
		      String ltype = Main.mapping.get(current_class).get(current_method).typeCheck(lvalue, "variable");
		      
		      if (ltype == null)
		    	  throw new RuntimeException("Error at new int [] expression");
		      if (!ltype.equals("int"))
		    	  throw new RuntimeException("Error at new int [<x>] expression: <x> must be int");
		      
		      return "int[]";
		      
		   }

		   /**
		    * f0 -> "new"
		    * f1 -> Identifier()
		    * f2 -> "("
		    * f3 -> ")"
		    */
		   public String visit(AllocationExpression n, String argu) {
		      n.f0.accept(this, argu);
		      String lvalue = n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      
//		      String ltype = Main.mapping.get(current_class).get(current_method).typeCheck(lvalue, "variable");
		      if (!Main.globalScope.containsKey(lvalue))
		    		  throw new RuntimeException("Error at new ID() expression: ID does not exist");
		      
		      return lvalue + ",className";
		   }

		   /**
		    * f0 -> "!"
		    * f1 -> Clause()
		    */
		   public String visit(NotExpression n, String argu) {
		      n.f0.accept(this, argu);
		      String retval = n.f1.accept(this, argu);
		      return retval;
		   }

		   /**
		    * f0 -> "("
		    * f1 -> Expression()
		    * f2 -> ")"
		    */
		   public String visit(BracketExpression n, String argu) {
		      n.f0.accept(this, argu);
		      String ret = n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      
		      return ret;
		   }
		   
}