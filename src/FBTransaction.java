package testClasses;
import java.math.BigDecimal;
import java.util.Date;

/* Code changed by Howard and Dapo*/


public class FBTransaction {

private String name;
    private BigDecimal value;
    private Integer category;
    private Date timeStamp;

    public FBTransaction(String name, BigDecimal value, Integer category) {
    setName(name);
        setValue(value);
        setCategory(category);
        timeStamp = new Date();
    }
   
    //A default constructor for if no variables are passed, creates an empty FBTransaction with unkown category
    public FBTransaction() {
        name = "[Pending FBTransaction]";
        value = BigDecimal.ZERO;
    }
   
    //null constructor with added parameter
    public FBTransaction(String name, BigDecimal value, Integer category,Date timeStamp) {
        name = null;
        value = null;
        category = 0;
        timeStamp = null;
    }

    // getName function fetches the name set
    public String getName() {
        return name;
    }

    // setName function sets the value of name,
    public String setName(String name) {
        if(name != null) {
    
            // if statement used to truncate if the string is longer than 25 characters
            if (name.length() > 25) {
                return name.substring(0,25);
            }
        }
            return this.name = name;
    }

    // returns bigdecimal value set
    public BigDecimal getValue() {
        return value;
    }
   
    //sets the bigDecimal value, anything that isnt valid will throw an illegal argument exception
    public void setValue(BigDecimal value) {
    if(value != null) {
    if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("This isnt Valid");
        }
        this.value = value;
    }
    }
   
    //fetches category value
    public Integer getCategory() {
        return category;
    }

    //sets the category value, used integer instead of int to be able to have a null value set
    //Illegal exception will be thrown if a value <= 0 is set
    public Integer setCategory(Integer category) {
    
        if (category != null) {
            if (category < 0) {
                throw new IllegalArgumentException("FBTransaction category isnt valid");
            }
        }
            return this.category = category;
    }
   
    //fetches the date value
    public Date getTimestamp() {
        return timeStamp;
    }
   
    //sets the date value and if eqivalent to null throws and illgeal argument exception
    public void setTimestamp(Date timeStamp) {
        if (timeStamp == null) {
            throw new IllegalArgumentException("Illegal Time!");
        }
        this.timeStamp = timeStamp;
    }
   
   
    /*Created an isComplete function that tests for potential values in name and or value*/
    public boolean isComplete(FBTransaction track) {

        String nameTrack = track.getName();
        BigDecimal valueTrack = track.getValue();
    
        //both values arent null = true
        if(nameTrack != null && valueTrack != null) {
            return true;
        }
        //name is null but value isnt
        else if (nameTrack == null && valueTrack != null) {
            track.setName("Settable");
        }
        //name isnt null and value is
        else if (nameTrack != null && valueTrack == null) {
            track.setValue(new BigDecimal(10.10));
        }
        //otherwise return false
        else {
            return false;
        }
        return false;
    }    

    @Override
    public String toString() {
        return name + " - £" + value.toString();
    }


}