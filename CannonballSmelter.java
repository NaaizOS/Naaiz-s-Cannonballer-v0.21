package CannonballSmelter;
import java.awt.Graphics2D;
import java.util.ArrayList;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;


@ScriptManifest(name = "Cannonball Smelter", author = "Naaiz", version = 0.2, info = "", logo = "") 
public class CannonballSmelter extends Script {
	ArrayList<Task> tasks = new ArrayList<Task>();
	public static Area edgeBank = new Area(3098,3497,3095,3494); // Area in edge bank
	public static Area smeltHere = new Area(3109,3497,3103,3500); // Area at furnace where furnace is reachable
	
    @Override
    public void onStart() {
    	tasks.add(new BankTask(this));
    	tasks.add(new SmeltTask(this));
    	tasks.add(new InterruptedSmelting(this));
    	tasks.add(new WalkToBank(this));
    	tasks.add(new WalkToFurnace(this));
    	tasks.add(new PathingFix(this));
    }
    
    @Override
    public void onExit() throws InterruptedException {
        log("Quitting Cannonball Smelter... Thanks for running!");
    }
    

    @Override
    public int onLoop() throws InterruptedException {
    	log(myPosition());
    	for (Task task : tasks) {
    		log("Current task: " + task);
    	    task.run();
    	}
		return random(1000,2750);
    }


    @Override
    public void onPaint(Graphics2D gr) {
    	int x = getMouse().getPosition().x;
        int y = getMouse().getPosition().y;              
        gr.drawLine(0, y, 765, y);
        gr.drawLine(x, 0, x, 503);
    }
}