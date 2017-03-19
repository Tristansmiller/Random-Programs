import java.util.ArrayList;
import java.util.List;

public class RipCriteria implements Criteria {

	@Override
	public List<Hammer> meetCriteria(List<Hammer> hammers) {
		List<Hammer> ripHammers = new ArrayList<Hammer>();
		
		for(Hammer ham: hammers){
			if(ham.getClaw()==1)
				ripHammers.add(ham);
		}
		return ripHammers;
	}

	
}
