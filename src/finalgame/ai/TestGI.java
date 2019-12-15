package finalgame.ai;

import finalgame.engineAdditions.GameObject;
import finalgame.engineAdditions.HealthComponent;
import finalgame.engineAdditions.AIBehaviorComponent;

import java.util.ArrayList;

import engine.ai.GroupInformation;

public class TestGI extends GroupInformation {

	private ArrayList<GameObject> objects;
	private GameObject toHeal;
	private ArrayList<GameObject> healers;
	
	public TestGI() {
		super();
		objects = new ArrayList<GameObject>();
		healers = new ArrayList<GameObject>();
	}
	
	public void addObject(GameObject o) {
		objects.add(o);
		if (o.getName().contains("HEALER")) {
			healers.add(o);
		}
	}
	
	
	@Override
	public void tick(long nanos) {
		double highest = 0;
		for (GameObject o: objects) {
			try {
				HealthComponent h = (HealthComponent) o.getComponent("HEALTH");
				if (h.getHealth()<=0) {
					continue;
				}
				double damage = h.getTotalHealth()-h.getHealth();
				if (damage>0 && damage>highest && h.getHealth()>0) {
					highest = damage;
					toHeal = o;
				}
				
			} catch(NullPointerException e) {
				
			}
		}
		if (highest == 0) {
			toHeal = null;
		}
		for (GameObject h: healers) {
			AIBehaviorComponent ai = (AIBehaviorComponent) h.getComponent("BEHAVIOR");
			try {
				((NotInRange) ai.getBT().getBehavior(2)).setTarget(toHeal);
				((MoveTo) ai.getBT().getBehavior(3)).setTarget(toHeal);
			} catch(NullPointerException e) {
				
			}
			
		}
		
	}
	
	public HealthComponent getHeal() {
		return (HealthComponent) toHeal.getComponent("HEALTH");
	}
	
}
