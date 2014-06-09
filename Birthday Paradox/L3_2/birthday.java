import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class birthday 
{
 int match_Y,match_N;
 public static final Random randGen = new Random();  
 
 birthday()   // Default constructor.
 {
  match_Y=0;   // Keep track of trials where matching pair is found and not found.
  match_N=0;
 }	 
 
 float simulate(int nPeople, int nTrials) // Returns the probability of two people having the same birthday
 {                                        // for given n people and n trials.
  int n = 0;
  for(int i = 0; i < nTrials; i++) 
  {
   if(trial(nPeople))                // For each trial a set backed by a hash table is created and checked at the same time for matches
	n++;              // Match found.
   else
	 match_N++;       // Match not found.
  }
  match_Y=n;
  return (float)n/nTrials;  // Probability = No.of matches found / No.of trials performed.
 }

 boolean trial(int nPeople)  // Returns true if match found else false.
 {
  Set<Integer> birthdays = new HashSet<Integer>(); // Constructs a new, empty set interface backed by has table. 
                                                   // The backing HashMap instance has default initial capacity (16) and load factor (0.75).
  for (int i = 0; i < nPeople; i++) 
  {
   int day = randGen.nextInt(365);
   if (birthdays.contains(day))   // Returns true if the set contains the specified element.
	return true;
   birthdays.add(day);  // Adds specified element to the set if not already present.
  }
  return false;  // If no match in generated set return false.
 }

}