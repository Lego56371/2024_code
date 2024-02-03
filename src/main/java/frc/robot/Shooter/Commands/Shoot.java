package frc.robot.Shooter.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Shooter.Shooter;
import frc.robot.Shooter.ShooterVelocities;

public class Shoot extends Command
{
    private Shooter s_shooter;
    private boolean finished = false;

    public Shoot(Shooter s_shooter)
    {
        this.s_shooter = s_shooter;
        addRequirements(s_shooter);
        finished = false;
    }//end shooter constructor

    @Override
    public void initialize() 
    {}//end initialize

    @Override
    public void execute() 
    {
        /*if(!s_intake.is_pieceInQueue())
        {
            s_intake.run_intake();
        }
        else
        {
            finished = true;
        }*/
        s_shooter.shoot(ShooterVelocities.closeup_leftSpeed, ShooterVelocities.closeup_rightSpeed);


    }


   @Override
   public void end(boolean interrupted) {
       // TODO Auto-generated method stub
       s_shooter.stop_shooter();
   }


    //TODO add finished
    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub


        return finished;
    }

    
}
