package CannonballSmelter;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class SmeltTask extends Task {

	public SmeltTask(MethodProvider api) {
		super(api);
	}
	
	@Override
	public boolean canProcess() {
		return api.getInventory().isFull() && CannonballSmelter.smeltHere.contains(api.myPlayer()) && api.getInventory().contains("Ammo mould") && !api.inventory.isItemSelected();
	}

	@Override
	public void process() {
		api.inventory.getItem("Steel bar").interact("Use");
		if(api.inventory.isItemSelected()) {
			api.getObjects().closest("Furnace").interact("Use");
		}
		api.mouse.moveRandomly();
		new ConditionalSleep(3000) {
			@Override
            public boolean condition() throws InterruptedException {
				return api.widgets.getWidgetContainingText("Steel bar") != null && api.widgets.getWidgetContainingText("Steel bar").isVisible();
            }
		}.sleep();
        if(api.myPlayer().getPosition().getX() == 3109 && api.myPlayer().getPosition().getY() == 3499) {
	        api.widgets.interact(309, 6, "Make All");
	        api.mouse.moveOutsideScreen();
			new ConditionalSleep(170000, 1000) {
	            @Override
	            public boolean condition() throws InterruptedException {
	                return !api.inventory.contains("Steel bar");
	            }
	        }.sleep();
        }
	}		
}


