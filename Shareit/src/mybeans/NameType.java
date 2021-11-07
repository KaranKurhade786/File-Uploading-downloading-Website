package mybeans;

public class NameType {
	String name,type,path;
	int c;

	public NameType(int c) {
		name="";
		type="";
		this.c=c;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setPath(String path) {
		this.path = path;
	}


	public void process()
	{
		int i,j;
		for(i=1;i<path.length();i++)
		{
			if((path.charAt(path.length()-i))=='.')
			{
				//f=path.length()-i;
				j=(path.length()-i)+1;
				for(;j<path.length();j++)
				{
					type+=path.charAt(j);
				}
				break;					
			}
		}
		
		for(c+=1;c<path.length();c++)
		{
			name+=path.charAt(c);
		}
	}


}
