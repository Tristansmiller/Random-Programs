import java.util.ArrayList;
import java.util.List;

public class CurvedCriteria implements Criteria {

	@Override
	public List<Hammer> meetCriteria(List<Hammer> hammers) {
		List<Hammer> curveHammers = new ArrayList<Hammer>();
		
		for(Hammer ham: hammers){
			if(ham.getClaw()==3)
				curveHammers.add(ham);
		}
		return curveHammers;
	}

	
}
