import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

public class LCS_Arnav_Ahire_arnavane {

	public static void main(String[] args) throws IOException {
		
		// For scanning the input from input file
		
				File file = new File("input.txt");
				Scanner scan=new Scanner(file);
				
		// For putting the output from console to output file
				
				File output=new File("output.txt");
				FileOutputStream fos=new FileOutputStream(output);
				PrintStream ps=new PrintStream(fos);
				System.setOut(ps);
						
				LinkedList strings=new LinkedList<>();  // We scan the input for all the strings hence we store these strings in a LinkedList
				
				while(scan.hasNextLine())
				{
					strings.add(scan.nextLine());    // Read and store every string on next line
				}
				
				String String1=(String) strings.get(0);
				char[] a=String1.toCharArray();			// Convert 1st string to char array and set the length of this array
				int n=a.length;
				if(n>1000)
				{
					System.out.println("You have entered wrong input. Please provide strings of length less than 1001");
					System.exit(0);
				}
				
				String String2=(String) strings.get(1); // Convert 2nd string to char array and set the length of this array
				char[] b=String2.toCharArray();
				int m=b.length;
				if(m>1000)
				{
					System.out.println("You have entered wrong input. Please provide strings of length less than 1001");
					System.exit(0);
				}
				
				int[][] opt=new int[n+1][m+1];			// opt array and pie array are greater than a and b array in length by 1
				String[][] pie=new String[n+1][m+1];
				LinkedList S=new LinkedList<>();		// S is the solution set				
				
		// To check if the strings from input.txt have been received correctly	
				
		//	System.out.println(String1+" "+String2);
				
		//Longest Common Subsequence Program starts
				
				try
				{
				
					for(int j=0;j<=m;j++)
					{
						opt[0][j]=0;				// set the 0th row as zero
					}
	
					for(int i=1;i<=n;i++)	
					{
						opt[i][0]=0;				// set the 0th column as zero
						for(int j=1;j<=m;j++)
						{
							if(a[i-1]==b[j-1])				// we take a-1 as the size of a array and b array is less by opt and pie array by 1. Hence a[i-1] and b[i-1] will be the last elements	
							{
								opt[i][j]=opt[i-1][j-1]+1;		// if the (i-1)th element of a and b match then give it "</" sign and add +1
								pie[i][j]="</";
							}
							else if(opt[i][j-1]>=opt[i-1][j])	// if no match and if (i,j-1)th element is greater than (i-1,j)th element give it "<-" sign and assign (i,j-1) to (i,j)
							{
								opt[i][j]=opt[i][j-1];
								pie[i][j]="<-";
							}
							else								// if no match and if (i,j-1)th element is LESSER than (i-1,j)th element give it "^|" sign and assign (i-1,J) to (i,j)
							{
								opt[i][j]=opt[i-1][j];
								pie[i][j]="^|";
							}
							//			System.out.print(opt[i][j]);
						}
							//	System.out.println();	
					}

					int i=n;			// length of string a
					int j=m;			// length of string b
					while(i>0 && j>0)
					{
						if(pie[i][j]=="</") // if a,b match then we add the element to solution set and reduce i and j value
						{
							S.add(a[i-1]);
							i=i-1;
							j=j-1;
						}
						else if(pie[i][j]=="^|") // else we reduce i value
						{
							i=i-1;
						}
						else					// else we reduce j value
						{
							j=j-1;
						}
					}
				}
				catch(Exception e)
				{
					System.out.println("An exception occurred");
					System.exit(0);
				}
				
				// Print the output  
		
				System.out.println(S.size());   // We print the size of the longest sub sequence
		
				for(int k=S.size()-1;k>=0;k--)  // We print the longest common subsequence
				{
					System.out.print(S.get(k));
				}
		}
}
