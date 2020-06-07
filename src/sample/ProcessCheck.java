package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessCheck {

	public boolean isRun(String process) {
		try {
			Process p = new ProcessBuilder("tasklist").start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			try {
				while(true) {
					String line = br.readLine();
					if(line == null) {
						break;
					}
					if(line.startsWith(process)) {
						return true;
					}
				}
			}finally {
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

}
