package singleton;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.junit.Test;
import com.singletons.A;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;

public class SingletonTest {
	
	JavaClasses classes = new ClassFileImporter().importPackages("com.singletons");
	
	@Test
	public void hasStaticFactoryMethodTest() throws NoSuchMethodException, SecurityException {
		methods()
		.that()
		.haveName(A.class.getMethod("getInstance").getName())
		.should()
		.haveRawReturnType(String.class)
		.andShould().beStatic()
		.andShould().bePublic()
		.because("'a singleton should have a public static getInstance() method that always returns the same instance'")
		.check(classes);
	}
	
	@Test
	public void privateConstructorOnlyTest() {
		classes()
		.should()
		.haveOnlyPrivateConstructors()
		.because("'The only way to get instance of singleton is to use getInstance() method'")
		.check(classes);
	}
	
	@Test
	public void hasStaticMemberTest() {
		 fields()
		 .that()
		 .haveRawType(A.class)
         .should().bePrivate()
         .andShould().beStatic()
         .because("'a singleton should have a static member of its own type'")
         .check(classes);
	}
	
	
}
