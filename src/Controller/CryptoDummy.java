package Controller;

import java.io.*;  

public	class	CryptoDummy
{
	private		byte[]	textoDecifrado;
	
	
	public	CryptoDummy()
	{
		textoDecifrado = null;
	}
	
    public	void	geraDecifra(byte[] texto, File fDummy)	throws	IOException, ClassNotFoundException
    {
    	ObjectInputStream ois = new ObjectInputStream (new FileInputStream (fDummy));  
        int	iDummy = (Integer) ois.readObject();
        ois.close();
        textoDecifrado = texto;
        for(int i = 0; i < texto.length; i++)
        {
        	textoDecifrado[i] = (byte) (textoDecifrado[i] - i - iDummy);
        }
    }

    public	byte[]	getTextoDecifrado()	throws	Exception
    {
    	return	textoDecifrado;
    }
}
