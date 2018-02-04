package lab.model;

import org.springframework.beans.factory.annotation.Autowired;

public class ApuBar implements Bar {

    @Autowired
	public Squishee sellSquishee(Person customer)  {
        if (customer.isBroke())
            throw new CustomerBrokenException();

        System.out.println("Here is your Squishee \n");
        return new Squishee("Usual Squishee");
    }
}