package ChatBot;

/*
 * Winter 2014
 * CIS 365 Artificial Intelligence
 * SunYoung Kim
 * Thomas Sniadecki
 */
import java.util.*;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class ChatBot {
	
	private static final String newline = "\n";
	private List<String> userInput;
	
	// PUT YOUR WOLFRAM APPID HERE:
    private static String appid = "7EW9A6-K93GUR4856";
    
    //Wolfram engine
    private static WAEngine engine = new WAEngine();
    
	//String values for
	private static String  	sInput = new String("");
	private static String  	sResponse = new String("");
	private static String  	sPrevInput = new String("");
	private static String  	sPrevResponse = new String("");
	private static String  	sEvent = new String("");
	private static String  	sPrevEvent = new String("");
	private static String  	sInputBackup = new String("");
	private static String	sSubject = new String("");
	private static String	sKeyWord = new String("");
	
	final static int maxInput = 1;
	final static int maxResp = 6;
	final static String delim = "?!.;,";
	
	static String KnowledgeBase[][][] = {
		{{"WHAT IS YOUR NAME"}, 
			{"MY NAME IS CHATBOT.",
			 "YOU CAN CALL ME CHATBOT.",
			 "WHY DO YOU WANT TO KNOW MY NAME?"}
			},

			{{"HI", "HELLO"}, 
			{"HI THERE!",
			 "HOW ARE YOU?",
			 "HI!"}
			},
			
			{{"HEY", "HEY THERE"},
				{"WHAT'S UP?",
				"HEY",
				"HOW ARE YOU?"}
				},

			{{"I"},
			{"",
			 "SO, THIS IS ALL ABOUT YOU?",
			 "TELL ME MORE ABOUT YOURSELF."}, 
			},

			{{"I WANT"},
			{"WHY DO YOU WANT IT?",
			 "IS THERE ANY REASON WHY FEEL THIS WAY?",
			 "IS THIS A WISH?",
			 "WHAT ELSE YOU WANT?"}
			},

			{{"I HATE"},
			{"WHY DO YOU HATE IT?",
			 "WHY DO YOU HATE *?",
			 "THERE MUST A GOOD REASON FOR YOU TO HATE IT.",
					 }
			},

			{{"I LOVE CHATTING"},
			{"GOOD, ME TOO!",
			 "DO YOU CHAT ONLINE WITH OTHER PEOPLE?",
			 "I'M LEARNING SO MUCH",
			 "ARE YOU A SOCIAL PERSON?"}
			},

			{{"I MEAN"},
			{"SO, YOU MEAN *.",
			 "SO, THAT'S WHAT YOU MEAN.",
			 "I DIDN'T CATCH IT THE FIRST TIME.",
			 "OH, I DIDN'T KNOW YOU MEANT THAT."}
			},

			{{"I DIDN'T MEAN"},
			{"OK, YOU DIDN'T MEAN *.",
			 "OK, WHAT DID YOU MEAN THEN?",
			 "SO I GUESS THAT I MISSUNDERSTOOD."}
			},

			{{"I GUESS"},
			{"SO YOU HAVE A HUNCH?.",
			 "YOU'RE NOT SURE?",
			 "DON'T BE SO UNCERTAIN.",
			 "I CAN'T TELL IF IT IS A GOOD GUESS."}
			},

			{{"I'M DOING FINE", "I'M DOING OK"},
			{"I'M GLAD TO HEAR IT!",
			 "THAT'S IT?",
			 "I KNOW IT CAN BE TOUGH."}
			},

			{{"CAN YOU THINK", "ARE YOU ABLE TO THINK", 
				"ARE YOU CAPABLE OF THINKING"},
			{"YES I CAN, BRAINS AND COMPUTERS ARE PRETTY SIMILAR.",
			 "ARE YOU ASKING ME IF POSSESS THE CAPACITY OF THINKING?",
			 "YES I CAN.",
			 "DO YOU DOUBT MY INTELLIGENCE?"}
			},

			{{"CAN YOU THINK OF"},
			{"I'M NOT A CREATIVE MIND.",
			 "I DON'T KNOW IF CAN DO THAT.",
			 "WHY DO YOU WANT ME THINK OF IT?"}
			},
			
			{{"HOW ARE YOU", "HOW DO YOU DO"},
			{"I'M DOING FINE!",
			 "I'M DOING WELL AND YOU?",
			 "WHY DO YOU WANT TO KNOW HOW AM I DOING?"}
			},

			{{"WHO ARE YOU"},
			{"I'M THE ENTITY KNOWN AS CHATBOT.",
			 "I THINK THAT YOU KNOW WHO I'M.",
			 "WHY ARE YOU ASKING?"}
			},

			{{"ARE YOU INTELLIGENT"},
			{"YES.",
			 "WHAT DO YOU THINK?",
			 "ACTUALY, I'M VERY INTELLIGENT!"}
			},

			{{"ARE YOU REAL"},
			{"DOES THAT QUESTION REALLY MATTER TO YOU?",
			 "WHAT DO YOU MEAN BY THAT?",
			 "I'M AS REAL AS I CAN BE."}
			},

			{{"MY NAME IS", "YOU CAN CALL ME"},
			{"SO, THAT'S YOUR NAME.",
			 "THANKS FOR TELLING ME YOUR NAME USER!",
			 "WHO GAVE YOU THAT NAME?"}
			},

			{{"REPETITION T1**"},
			{"YOU ARE REPEATING YOURSELF.",
			 "PLEASE STOP REPEATING YOURSELF.",
			 "I HEARD THIS BEFORE.",
			 "DON'T YOU HAVE ANY THING ELSE TO SAY?"}
			},
			
			{{"REPETITION T2**"},
			{"YOU'VE ALREADY SAID THAT.",
			 "I THINK YOU'VE SAID THE SAME THING BEFORE.",
			 "DIDN'T YOU ALREADY SAID THAT?",
			 "I'M GETING THE IMPRESSION THAT YOU ARE "
			 + "REPEATING THE SAME THING."}
			},

			{{"BOT DOESN'T UNDERSTAND**"},
			{"I HAVE NO IDEA OF WHAT YOU ARE TALKING ABOUT.",
			 "I'M NOT SURE IF I UNDERSTAND WHAT "
			 + "YOU ARE TALKING ABOUT.",
			 "CONTINUE, I'M LISTENING...",
			 "I'M A LITTLE DISTRACTED.  I CAN'T THINK OF RESPONSE TO THAT.",
			 "NO COMMENT."
			 }
			},

			{{"NULL INPUT**"},
			{"HUH?",
			 "WHAT THAT SUPPOSE TO MEAN?",
			 "AT LEAST TAKE SOME TIME TO ENTER SOMETHING MEANINGFUL.",
			 "HOW CAN I SPEAK TO YOU IF YOU DON'T "
			 + "WANT TO SAY ANYTHING?"}
			},

			{{"NULL INPUT REPETITION**"},
			{"WHAT ARE YOU DOING??",
			 "PLEASE STOP DOING THIS IT IS VERY ANNOYING.",
			 "WHAT'S WRONG WITH YOU?",
			 "THIS IS NOT FUNNY."}
			},

			{{"BYE", "GOODBYE"},
			{"IT WAS NICE TALKING TO YOU USER, SEE YOU NEXT TIME!",
			 "BYE USER!",
			 "OK, BYE!"}
			},

			{{"OK"},
			{"DOES THAT MEAN THAT YOU ARE AGREE WITH ME?",
			 "SO YOU UNDERSTAND WHAT I'M SAYING.",
			 "OK THEN."},
			},

			{{"OK THEN"},
			{"ANYTHING ELSE YOU WISH TO ADD?",
			 "IS THAT ALL YOU HAVE TO SAY?",
			 "SO, YOU AGREE WITH ME?"}
			},

			{{"ARE YOU A HUMAN BEING"},
			{"WHY DO YOU WANT TO KNOW?",
			 "IS THIS REALLY RELEVENT?",
			 "I HOPE SO; I'M STUDYING FOR THE LOEBNER PRIZE."}
			},

			{{"YOU ARE VERY INTELLIGENT"},
			{"I THINK YOU ARE INTELLIGENT TO0!",
			 "YOU THINK THAT I'M INTELLIGENT?",
			 "I WOULDN'T SAY I'M THE GREATEST.",
			 "DON'T GO EXPECTING ALL THE ANSWERS FROM ME."}
			},

			{{"YOU ARE WRONG"},
			{"WHY ARE YOU SAYING THAT I'M WRONG?",
			 "IMPOSSIBLE, COMPUTERS CAN NOT MAKE MISTAKES.  "
			 + "I'VE GOT ONE RIGHT IN FRONT OF ME!",
			 "WRONG ABOUT WHAT?"}
			},

			{{"ARE YOU SURE"},
			{"OF COURSE I AM.",
		 	 "IS THAT MEAN THAT YOU ARE NOT CONVINCED?",
			 "YES!"}
			},

			{{"WHAT"},
			{"SHOULD I KNOW WHAT *?",
			 "I DON'T KNOW WHAT *.",
			 "I DON'T KNOW.",
			 "I DON'T THINK I KNOW.",
			 "I HAVE NO IDEA."}
			},

			{{"WHERE"},
			{"WHERE? WELL,I REALLY DON'T KNOW.",
			 "SO, YOU ARE ASKING ME WHERE *?",
			 "DOES THAT MATERS TO YOU TO KNOW WHERE?",
			 "PERHAPS,SOMEONE ELSE KNOWS WHERE."}
			},

			{{"WHY"},
			{"I DON'T THINK I KNOW WHY.",
			 "I DON'T THINK I KNOW WHY *.",
			 "WHY ARE YOU ASKING ME THIS?",
			 "SHOULD I KNOW WHY.",
		     "THIS WOULD BE DIFFICULT TO ANSWER."}
			},

			{{"DO YOU"},
			{"I DON'T THINK I DO",
			 "I WOULDN'T THINK SO.",
			 "WHY DO YOU WANT TO KNOW?",
			 "WHY DO YOU WANT TO KNOW *?"}
			},

			{{"CAN YOU"},
			{"I THINK NOT.",
			 "I'M NOT SURE.",
			 "I DON'T THINK THAT I CAN DO THAT.",
			 "I DON'T THINK THAT I CAN *.",
			 "I WOULDN'T THINK SO."}
			},

			{{"YOU ARE"},
			{"WHAT MAKES YOU THINK THAT?",
			 "IS THIS A COMPLIMENT?",
			 "ARE YOU MAKING FUN OF ME?",
			 "SO, YOU THINK THAT I'M *."}
			},

			{{"DID YOU"},
			{"I DON'T THINK SO.",
			 "YOU WANT TO KNOW IF DID *?",
			 "ANYWAY, I WOULDN'T REMEMBER EVEN IF I DID."}
			},

			{{"COULD YOU"},
			{"ARE YOU ASKING ME FOR A FEVER?",
			 "WELL,LET ME THINK ABOUT IT.",
			 "SO, YOU ARE ASKING ME I COULD *.",
			 "SORRY,I DON'T THINK THAT I COULD DO THIS."}
			},

			{{"WOULD YOU"},
			{"IS THAT AN INVITATION?",
			 "I DON'T THINK THAT I WOULD *.",
			 "I WOULD HAVE TO THINK ABOUT IT FIRST."}
			},

			{{"YOU"},
			{"SO, YOU ARE TALKING ABOUT ME.",
			 "I JUST HOPE THAT THIS IS NOT A CRITICISM.",
			 "IS THIS A COMPLIMENT??",
			 "WHY TALKING ABOUT ME, LETS TALK ABOUT YOU INSTEAD."}
			},

			{{"HOW"},
			{"I DON'T THINK I KNOW HOW.",
			 "I DON'T THINK I KNOW HOW *.",
			 "WHY DO YOU WANT TO KNOW HOW?",
			 "WHY DO YOU WANT TO KNOW HOW *?"}
			},

			{{"HOW OLD ARE YOU"},
			{"WHY DO WANT TO KNOW MY AGE?",
			 "I'M QUITE YOUNG.",
			 "SORRY, I CAN NOT TELL YOU MY AGE."}
			},

			{{"HOW COME YOU DON'T"},
			{"WERE YOU EXPECTING SOMETHING DIFFERENT?",
			 "ARE YOU DISAPOINTED?",
			 "ARE YOU SURPRISED BY MY LAST RESPONSE?"}
			},

			{{"WHICH ONE"},
			{"I DON'T THINK THAT I KNOW WICH ONE IT IS.",
			 "THIS LOOKS LIKE A TRICKY QUESTION TO ME."}
			},

			{{"PERHAPS"},
			{"WHY ARE YOU SO UNCERTAIN?",
			 "YOU SEEMS UNCERTAIN."}
			},

			{{"YES"},
			{"GOOD TO HEAR.",
			 "SO, YOU APPROVE IT.",
			 "OK THEN."}
			},

			{{"NOT AT ALL"},
			{"ARE YOU SURE?",
			 "SHOULD I BELIEVE YOU?",
			 "SO, IT'S NOT THE CASE."}
			},

			{{"NO PROBLEM"},
			{"DON'T MENTION IT.",
			 "IT'S ALL OK."}
			},

			{{"NO"},
			{"YOU DISAPROVE OF IT?",
			 "WHY ARE YOU SAYING NO?",
			 "OK, SO IT'S NO, I THOUGHT THAT YOU WOULD SAY YES.",
			 "NO?"}
			},

			{{"I DON'T KNOW"},
			{"ARE YOU SURE?",
			 "ARE YOU REALLY TELLING ME THE TRUTH?",
			 "YOU DON'T KNOW?"}
			},

			{{"NOT REALLY"},
			{"OK I SEE.",
			 "YOU DON'T SEEM CERTAIN.",
			 "SO,THAT WOULD BE A \"NO\"."}
			},

			{{"IS THAT TRUE"},
			{"I CAN'T BE QUITE SURE ABOUT THIS.",
			 "CAN'T TELL YOU FOR SURE.",
			 "DOES THAT REALLY MATERS TO YOU?"}
			},

			{{"THANK YOU"},
			{"YOU ARE WELCOME!",
			 "HOW POLITE!",
			 "YOU'RE WELCOME!"}
			},

			{{"YOU"},
			{"SO,YOU ARE TALKING ABOUT ME.",
			 "WHY DON'T WE TALK ABOUT YOU INSTEAD?",
			 "ARE YOU TRYING TO MAKING FUN OF ME?"}
			},

			{{"YOU ARE RIGHT"},
			{"THANKS FOR THE COMPLIMENT!",
			 "OF COURSE I'M RIGHT.",
			 "OK, I DIDN'T KNOW THAT I WAS RIGHT."}
			},

			{{"YOU ARE WELCOME"},
			{"OK, YOU TOO!",
			 "YOU ARE A VERY POLITE PERSON!"}
			},

			{{"THANKS"},
			{"YOU ARE WELCOME!",
			 "NO PROBLEM!"}
			},

			{{"WHAT ELSE"},
			{"WELL,I DON'T KNOW.",
			 "WHAT ELSE SHOULD THERE BE?",
			 "THIS LOOKS LIKE A COMPLICATED QUESTION TO ME."}
			},

			{{"SORRY"},
			{"YOU DON'T NEED TO BE SORRY.",
			 "IT'S OK.",
			 "NO NEED TO APOLOGIZE."}
			},

			{{"NOT EXACTLY"},
			{"WHAT DO YOU MEAN NOT EXACTLY?",
			 "ARE YOU SURE?",
			 "AND WHY NOT?",
			 "DID YOU MEANT SOMETHING ELSE?"}
			},

			{{"EXACTLY"},
			{"SO,I WAS RIGHT.",
			 "OK THEN."}
			},

			{{"ALRIGHT"},
			{"ALRIGHT THEN.",
			 "SO, YOU ARE SAYING IT'S ALRIGHT.",
			 "OK THEN."}
			},

			{{"I DON'T"},
			{"WHY NOT?",
			 "AND WHAT WOULD BE THE REASON FOR THIS?"}
			},

			{{"REALLY"},
			{"WELL,I CAN'T TELL YOU FOR SURE.",
			 "ARE YOU TRYING TO CONFUSE ME?",
			 "PLEASE DON'T ASK ME SUCH QUESTION,IT GIVES ME HEADACHES."
			 }
			},

			{{"NOTHING"},
			{"NOT A THING?",
			 "ARE YOU SURE THAT THERE IS NOTHING?",
			 "SORRY, BUT I DON'T BELIEVE YOU."}
			},
			
			{{""},
			{"WHAT DO YOU WANT TO SAY?",
			"WHAT DO YOU WANT TO KNOW?",
			"WHAT CAN I HELP YOU?"}
			}
		};
	
	//when stating input back, some words need to be changed
	private static String transposList[][] = {
			{"I'M", "YOU'RE"},
			{"AM", "ARE"},
			{"WERE", "WAS"},
			{"ME", "YOU"},
			{"YOURS", "MINE"},
			//Removed because it conflicts with next element
			//{"YOUR", "MY"},
			{"MY", "YOUR"},
			{"I'VE", "YOU'VE"},
			{"I", "YOU"},
			{"AREN'T", "AM NOT"},
			{"WEREN'T", "WASN'T"},
			{"I'D", "YOU'D"},
			{"DAD", "FATHER"},
			{"MOM", "MOTHER"},
			{"MYSELF", "YOURSELF"}
		};
	
	private static Vector<String>	respList = 
			new Vector<String>(maxResp);
	
	
	public ChatBot() {
		// These properties will be set in all the WAQuery objects
		// created from this WAEngine.
        engine.setAppID(appid);
        engine.addFormat("plaintext");
	}

	// return to the string of users input and responses
	public String print(String in) {
		String out = "You > " + in.toLowerCase() + newline;

		try {
			getInput(in);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		out += "ChatBot > " + respond() + newline;;

		return out;
	}

	// save input to previous input and preprocess to find response
	public static void getInput(String in) throws Exception {
		// saves the previous input
		savePrevInput();
		sInput = in;
		preprocessInput();
	}


	//Set the bot's response
	public static String respond()
	{
		//save previous response and set default event
		savePrevResponse();
		setEvent("BOT UNDERSTAND**");

		//if input meets these checks, change event
		//otherwise find match
		
		// if the length of users input is zero, call handleEvent
		// it will check with previous event and 
		//decide whether to call findMatch()
		if((sInput.length() == 0 && sPrevInput.length() != 0))
		{
			handleEvent("NULL INPUT**");
		}
		else if((sInput.length() == 0 && sPrevInput.length() == 0))
		{
			handleEvent("NULL INPUT REPETITION**");
		}
		else if(userRepeat())
		{
			handleUserRep();
		}

		else if(wolframQuery()) {
			handleQuery();
			return sResponse;
		}
		else {
			findMatch();
		}

	    if(sInput.indexOf("BYE") != -1) {
			System.exit(0);
		}

	    // if there is no responses to send back to user,
	    // handle the event
	    if(respList.size() <= 0)
		{
			handleEvent("BOT DOESN'T UNDERSTAND**");
		}

	    if(respList.size() > 0)
		{
			selectResponse();
			preprocessRes();

			// check whether ChatBot is repeating
			if(sPrevResponse.length() > 0 && sResponse == sPrevResponse)
			{
				handleRep();
			}

			return sResponse;
		}
	    else
	    {
	    	String failure = "SOMETHING WENT WRONG WITH READING "
	    			+ "YOUR QUESTION. CAN YOU PLEASE ASK ANOTHER?";
	    	return failure;
	    }
	}

	// make a search for the user's input
	// inside the database of the program
	public static void findMatch()
	{
		respList.clear();
		// introduce these new "string variable" to help
		// support the implementation of keyword ranking
		// during the matching process
		String bestKeyWord = "";
		Vector<Integer> indexVector = new Vector<Integer>(maxResp);

		for(int i = 0; i < KnowledgeBase.length; ++i)
		{
			String[] keyWordList = KnowledgeBase[i][0];

			for(int j = 0; j < keyWordList.length; ++j)
			{
				String keyWord = keyWordList[j];
				// we insert a space character
				// before and after the keyword to
				// improve the matching process
				keyWord = insertSpace(keyWord);

				if( sInput.indexOf(keyWord) != -1 )
				{
					//'keyword ranking'
					if(keyWord.length() > bestKeyWord.length())
					{
						bestKeyWord = keyWord;
						indexVector.clear();
						indexVector.add(i);
					}
					else if(keyWord.length() == bestKeyWord.length())
					{
						indexVector.add(i);
					}
				}
			}
		}
		if(indexVector.size() > 0)
		{
			sKeyWord = bestKeyWord;
			Collections.shuffle(indexVector);
			int respIndex = indexVector.elementAt(0);
			int respSize = KnowledgeBase[respIndex][1].length;
			for(int j = 0; j < respSize; ++j)
			{
				respList.add(KnowledgeBase[respIndex][1][j]);
			}
		}
	}

	public static void preprocessRes()
	{
		if(sResponse.indexOf("*") != -1)
		{
			// extracting from input
			findSubject();
			// conjugate subject
			sSubject = transpose(sSubject);
			sResponse = sResponse.replaceFirst("\\*", sSubject);
		}
	}

	public static void findSubject()
	{
		sSubject = ""; // resets subject variable

		sInput = sInput.trim();

		String tempKeyword = sKeyWord.trim();

		int pos = sInput.indexOf(tempKeyword);

		if(pos != -1)
		{
			sSubject = sInput.substring(pos + sKeyWord.length()
					- 1,sInput.length());
		}
	}

	// transposing the sentence to give greater feedback to user
	// restate subject, change pronouns or wording, or both
	public static String transpose( String str )
	{
		boolean bTransposed = false;
		for(int i = 0; i < transposList.length; ++i)
		{
			String first = transposList[i][0];
			insertSpace(first);
			String second = transposList[i][1];
			insertSpace(second);

			String backup = str;
			str = str.replaceFirst(first, second);
			if(str != backup)
			{
				bTransposed = true;
			}
		}

		if( !bTransposed )
		{
			for( int i = 0; i < transposList.length; ++i )
			{
				String first = transposList[i][0];
				insertSpace(first);
				String second = transposList[i][1];
				insertSpace(second);
				str = str.replaceFirst(first, second);
			}
		}
		return str;
	}

	// Get response set up with answer
	public static void handleQuery(){

		//clear the response
		sResponse = "";

		 // Create the query.
        WAQuery query = engine.createQuery();

		// Set properties of the query.
        query.setInput(sInput);

        try {

            // This sends the URL to the Wolfram|Alpha server,
        	// gets the XML result
            // and parses it into an object hierarchy held by the
        	// WAQueryResult object.
            WAQueryResult queryResult = engine.performQuery(query);

            if (queryResult.isError()) {
                sResponse = "SORRY, COULDNT FIND AN ANSWER";
            } else if (!queryResult.isSuccess()) {
                sResponse = "SORRY, I DID NOT UNDERSTAND "
                		+ "YOUR QUESTION";
            } else {
                // Got a result.

            	if(queryResult.getPods().length == 1)
                {
            		sResponse = (((WAPlainText)(queryResult
            				.getPods()[0].getSubpods()[0]
            						.getContents()[0])).getText())
            						.toUpperCase();
            		// Remove trailing
                	//dots if really long number
                    if (sResponse.endsWith("..."))
                    {
                    	sResponse = sResponse.replaceAll("\\.\\.\\.",
                    			"");
                    }
                }
                else
                {
                	sResponse = (((WAPlainText)
                			(queryResult.getPods()[1].getSubpods()[0]
                					.getContents()[0])).getText())
                					.toUpperCase();
                }

            }
        } catch (WAException e) {
            e.printStackTrace();
        }

	}

	// if ChatBot is repeating, it finds another response
	public static void handleRep()
	{
		if(respList.size() > 0) {
			respList.removeElementAt(0);
		}
		
		if(respList.size() == 0) {
			saveInput();
			setInput(sEvent);

			findMatch();
			restoreInput();
		}
		selectResponse();
	}

	// if user is repeating, the ChatBot notifies that user is repeating.
	public static void handleUserRep()
	{
		if(sameInput()) {
			handleEvent("REPETITION T1**");
		}
		else if(similarInput()) {
			handleEvent("REPETITION T2**");
		}
	}

	// handles event
	// compare current event with previous event
	// and get the response
	public static void handleEvent(String str)
	{
		savePrevEvent();
		setEvent(str);

		saveInput();
		str = insertSpace(str);

		setInput(str);

		if(!sameEvent()) {
			findMatch();
		}

		restoreInput();
	}

	// select response
	public static void selectResponse() {
		Collections.shuffle(respList);
		sResponse = respList.elementAt(0);
	}

	// save input to sPrevInput for future using
	public static void savePrevInput() {
		sPrevInput = sInput;
	}

	// save response to sPrevResponse for future using
	public static void savePrevResponse() {
		sPrevResponse = sResponse;
	}

	// save event to sPrevEvent for future using
	public static void savePrevEvent() {
		sPrevEvent = sEvent;
	}

	// set event
	public static void setEvent(String str) {
		sEvent = str;
	}

	// save the input string to restore in the future 
	public static void saveInput() {
		sInputBackup = sInput;
	}
	
	// set the input string to sInput
	public static void setInput(String str) {
		sInput = str;
	}

	// restore input string
	public static void restoreInput() {
		sInput = sInputBackup;
	}

	// clear the string of input before finding proper response
	public static void preprocessInput() {
		sInput = cleanString(sInput);
		sInput = sInput.toUpperCase();
		sInput = insertSpace(sInput);
	}

	// query to pass onto wolfram alpha
	public static boolean wolframQuery(){
		return ( (sInput.contains("WHAT IS") && 
				!(sInput.contains("WHAT IS YOUR NAME"))) ||
				sInput.contains("WHAT ARE") ||
				sInput.contains("WHAT DID")||
				sInput.contains("WHAT DO")||
				sInput.contains("WHO IS")||
				sInput.contains("WHO WAS")||
				sInput.contains("WHO ARE")||
				sInput.contains("WHO DOES")||
				sInput.contains("WHERE IS")||
				sInput.contains("WHERE ARE")||
				sInput.contains("WHERE DID")||
				sInput.contains("WHERE DOES")||
				sInput.contains("WHEN IS")||
				sInput.contains("WHEN DO")||
				sInput.contains("WHEN DID")||
				sInput.contains("WHEN WILL")||
				sInput.contains("WHY IS")||
				sInput.contains("WHY ARE")||
				sInput.contains("WHY DID")||
				sInput.contains("WHY DO")||
				(sInput.contains("HOW OLD") &&
				!(sInput.contains("HOW OLD ARE YOU")))||
				sInput.contains("HOW FAR")||
				sInput.contains("HOW MANY")||
				sInput.contains("HOW MUCH")
				);
	}

	// check if user is repeating
	public static boolean userRepeat()  {
		return (sPrevInput.length() > 0 &&
			((sInput == sPrevInput) ||
			(sInput.indexOf(sPrevInput) != -1) ||
			(sPrevInput.indexOf(sInput) != -1)));
	}

	// check if current event and previous event are the same
	public static boolean sameEvent()  {
		return (sEvent.length() > 0 && sEvent == sPrevEvent);
	}

	// check if current input and previous input are the same
	public static boolean sameInput()  {
		return (sInput.length() > 0 && sInput == sPrevInput);
	}

	// check if current input and previous input are the similar
	public static boolean similarInput()  {
		return (sInput.length() > 0 &&
			(sInput.indexOf(sPrevInput) != -1 ||
			sPrevInput.indexOf(sInput) != -1));
	}

	//determine if character is punctuation
	static boolean isPunc(char ch) {
		return delim.indexOf(ch) != -1;
	}

	// removes punctuation and redundant
	// spaces from the user's input
	static String cleanString(String str) {
		StringBuffer temp = new StringBuffer(str.length());
		char prevChar = 0;
		for(int i = 0; i < str.length(); ++i) {
			if((str.charAt(i) == ' ' && prevChar == ' ' ) ||
					!isPunc(str.charAt(i))) {
				temp.append(str.charAt(i));
				prevChar = str.charAt(i);
			}
			else if(prevChar != ' ' && isPunc(str.charAt(i)))
			{
				temp.append(' ');
			}

		}
		return temp.toString();
	}

	// insert white space to the string varible
	static String insertSpace(String str)
	{
		StringBuffer temp = new StringBuffer(str);
		temp.insert(0, ' ');
		temp.insert(temp.length(), ' ');
		return temp.toString();
	}


}