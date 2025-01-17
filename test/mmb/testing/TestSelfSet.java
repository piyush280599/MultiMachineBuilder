/**
 * 
 */
package mmb.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import monniasza.collects.Identifiable;
import monniasza.collects.selfset.HashSelfSet;
import monniasza.collects.selfset.SelfSet;

/**
 * @author oskar
 *
 */
class TestSelfSet {
	class TestValue{
		@Override
		public boolean equals(Object other) {
			return this == other;
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}
	}
	class TestContainer implements Identifiable<String>{
		String name;
		TestValue value;
		@Override
		public String id() {
			return name;
		}
		public void test(TestValue obj, String message) {
			assertEquals(obj, value, message);
		}
	}
	@Test
	void test() {
		SelfSet<String, TestContainer> set = HashSelfSet.createNonnull(TestContainer.class);
		TestContainer contA = new TestContainer();
		TestValue objA = new TestValue();
		contA.name = "A";
		contA.value = objA;
		set.add(contA);
		
		TestContainer contB = new TestContainer();
		TestValue objB = new TestValue();
		contB.name = "B";
		contB.value = objB;
		set.add(contB);
		
		TestContainer contC = new TestContainer();
		TestValue objC = new TestValue();
		contC.name = "C";
		contC.value = objC;
		set.add(contC);
		
		TestContainer retrievedA = set.get("A");
		Assert.assertNotNull("Entry A not properly set", retrievedA);
		retrievedA.test(objA, "Object A mismatch!");
		
		TestContainer retrievedB = set.get("B");
		Assert.assertNotNull("Entry B not properly set", retrievedB);
		retrievedB.test(objB, "Object B mismatch!");
		
		TestContainer retrievedC = set.get("C");
		Assert.assertNotNull("Entry C not properly set", retrievedC);
		retrievedC.test(objC, "Object C mismatch!");
		
	}

}
