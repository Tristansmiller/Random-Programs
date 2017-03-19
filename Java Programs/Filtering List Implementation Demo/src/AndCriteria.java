import java.util.List;

public class AndCriteria implements Criteria {

	private Criteria criteria1;
	private Criteria criteria2;
	
	public AndCriteria(Criteria c1, Criteria c2){
		criteria1=c1;
		criteria2=c2;
	}
	
	
	public List<Hammer> meetCriteria(List<Hammer> hammers) {
		List<Hammer> crit1List = criteria1.meetCriteria(hammers);
		return criteria2.meetCriteria(crit1List);
		
	}

}
