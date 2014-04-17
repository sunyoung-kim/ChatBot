<h1>ChatBot</h1>
<h3>Grand Valley State University</h3>

<p>Winter 2014
<br/>
CIS 365 - Artificial Intelligence</p>

<h5>AUTHORS:</h5>
<p>Thomas Sniadecki<br/>
SunYoung Kim</p>

<h5>INSTRUCTIONS FOR INSTALLATION:</h5>

<p>The .JAR file is capable of running the ChatBot program.  Simply run it through a machine with Java.</p>

<p>The files in the ChatBot zip is the source code from the Eclipse project.  You may import it into the IDE and adjust the classpaths and packages, or simply remove them.
The folders ChatBot/src/com/wolfram and ChatBot/src/lib hold WolframAlpha libraries and the HTTPClient respectively needed to interface with the WolframAlpha website for queries.  The .JAR files contained in this folder needs to be included as a classpath for the ChatBot program.  In Eclipse, this can be accomplished by importing the files into the project.</p>

<h5>ABOUT THE CODE:</h5>

<p>ChatBot is a NLP bot that attempts to hold a conversation with the user.  The user enters text, and the ChatBot responds based on keywords used.  The ChatBot also uses Wolfram Alpha to answer certain questions, should certain keywords be used.

<p>The program reads the user's input and saves it.  It proceeds to examine the string to determine if it's null or a repeat of the last sentence entered.  It then checks if the string contains a key phrase that would provide a useful result in Wolfram Alpha; variations on who, what, when, and so on.  If the user's input passes all these checks, the string's contents are then examined.</p>

<p>The string is compared with a "Knowledge Base" of keywords.  To ease the process of comparison, all punctuation is removed and the string is set to uppercase.  When the most relevant keyword (relevant being the most characters matching) in the string is located, one of the provided responses is randomly chosen and used as the ChatBot's response.  For conditions such as repeats or null strings, the user's entry is ignored and a default keyword is used instead.  This still gives a randomized response.</p> 
Sometimes a '*' is included in the response phrase, which is replaced with the subject of the sentence.  In this program, we only use the phrase after the keyword as the subject.  We also transpose the contents of the subject so the response makes more sense.  For example, "I hate my clunky car" can become "Why do you hate your clunky car?"</p>
<p>For Wolfram Alpha queries, a request is simply performed by the engine that is part of the Wolfram Alpha Java library.  Query results in Wolfram Alpha are in XML and separated in a "pod" structure.  The best result is simply chosen from the plaintext inside the first or second pod provided.</p>

<p>Finally, the user can close the program with a simple "bye" instead of closing the window.</p>
