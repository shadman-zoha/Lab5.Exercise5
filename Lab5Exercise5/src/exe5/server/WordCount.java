package exe5.server;

public class WordCount {


		public String getWordCount(String text)
		{
			int countWords=1;
			
			for(int i = 0 ; i<text.length(); i++)
			{
				char temp=text.charAt(i);
				if(Character.isWhitespace(temp))
				{
					if(Character.isJavaIdentifierStart(text.charAt(i-1)) 
							&& Character.isJavaIdentifierStart(text.charAt(i+1)))
					{
						++countWords;
					}
				}
				
			}
			
			return Integer.toString(countWords);
			
		}
	
	
	
}