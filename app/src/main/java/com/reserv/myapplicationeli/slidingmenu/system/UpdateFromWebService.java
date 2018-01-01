package com.reserv.myapplicationeli.slidingmenu.system;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.reserv.myapplicationeli.slidingmenu.db.main.CursorManager;
import com.reserv.myapplicationeli.slidingmenu.tools.Utility;
@SuppressLint("DefaultLocale")
public class UpdateFromWebService {
	private Context context;
	public static final int GET_FULL_UPDATE = 501;
	public static final int GET_STOCKS = 502;
	public static final int GET_CARTABLE_REQUESTS = 503;
	public static final int GET_REPORT_FACTS = 504;
	public static final int GET_DASHBOARD_INFOS = 505;
	public static final int GET_MESSAGES = 506;
	public static final int ACTIVATION_CHECKING = 507;
	public static final int CHECK_AWARDS = 508;
	public static final int SEND_POSITIONS = 509;
	public static final int SEND_LOGS = 510;
	//
	public static final int GET_NO_ORDER_REASONS=514;
	public static final int CONDITION = 511;
	public static final int CONDITION_DETAILS = 512;
	public static final int PRIZE = 513;
	public static final int PERMISSION_INV = 514;
	public static final int ACTIVATION_PERSON = 515;
	public static final int PROMOTION_DESC = 516;
	//
	public String receiverNumber = "";
	public static int EXTRA_VISITOR_ID = 0;
	public static int EXTRA_CUSTOMER_ID = 0;
	public static boolean EXTRA_IS_OPEN_FACTS = false;
	public String EXTRA_REQUEST = "";

	public String goodCod = "", goodName = "", prizeDetailId = "";
	private CursorManager cursorCheckAward;
	public static enum STATES {
		CONNECTING(1), NETWORK_UNAVAILABLE(2), SERVER_UNAVAILABLE(3), CONNECTED(4), PROCCESSING(5), SUCCESSFUL(6), FAILED(7), WAITING_FOR_RESPONSE(8), UPDATE_NEEDED(9), FETCHING_DATAS(10), AMOUNT_ERROR(11), SENDING_DATA(
				12), DOWNLOAD(13), ACTIVATION_FAILED(15), SMS_SENDING(16), SMS_SENT(17), SMS_SENDING_FAILED(18), ACTIVATION_CHECKING(19);
		private int paramValue;

		private STATES(int param) {
			this.paramValue = param;
		}

		public int getState() {
			return paramValue;
		}

		public static STATES state(int param) {
			for (STATES s : values()) {
				if (s.paramValue == param)
					return s;
			}
			return FAILED;
		}
	};


	public void execute(int methodCode) {
		new update().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,methodCode);
	}

	// =================================================================================
	private class update extends AsyncTask<Integer, Void, String> {
		@Override
		protected String doInBackground(Integer... arg0) {
			Log.e("UPDATE", arg0[0] + "");
			boolean successful = true;
			processingState(STATES.CONNECTING);
			if (Utility.isNetworkAvailable(context)) {
				if (Utility.isConnectionReachable(context)) {
					processingState(STATES.CONNECTED);
					processingState(STATES.PROCCESSING);
					switch (arg0[0]) {
				
					case SEND_POSITIONS:
						sendPosition();
						break;
			
					}
					if (successful) {
						processingState(STATES.SUCCESSFUL);
					} else
						processingState(STATES.FAILED);
				} else
					processingState(STATES.SERVER_UNAVAILABLE);
			} else
				processingState(STATES.NETWORK_UNAVAILABLE);
			return "";
		}//end doInBackground
		// =================================================================================	
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		}
	}//end update

	
	










	

	public void processingState(STATES connecting) {
		// TODO Auto-generated method stub
		
	}

	
	


	

	public void sendPosition() {
		if (Utility.isNetworkAvailable(context))
			if (Utility.isConnectionReachable(context)){}
				
					/*try {
						Position_Table db = new Position_Table(
								GlobalApplication.getContext());
						CursorManager positions = db.getPositions();
						while (positions.getCount() > 0) {
							String positionsStr = "";
							for (int i = 0; i < positions.getCount(); i++) {
								positions.moveToPosition(i);
								positionsStr += String.format("%s,%s^%s|",
												positions.getString(Position_Table.Columns.Latitude.value()),
												positions.getString(Position_Table.Columns.Longitude.value()),
												positions.getString(Position_Table.Columns.Date.value()));
							}
							if (positionsStr.length() > 0
									&& positionsStr.endsWith("|"))
								positionsStr = positionsStr.substring(0,
										positionsStr.length() - 1);
							
											positionsStr);
							Log.e("SAVE POSITION RESULT", result);
							if (result.length() > 0) {
								for (int i = 0; i < positions.getCount(); i++) {
									positions.moveToPosition(i);
									db.positionSent(positions
											.getInt(Position_Table.Columns.id.value()));
									if (i == positions.getCount() - 1)
										db.deleteOlderPositions(positions
												.getInt(Position_Table.Columns.id
														.value()));
								}
							}
							positions = db.getPositions();
						}
					} catch (Exception e) {
					}*/
	}

	// ========================================================================================
	// ========================================================================================
	// ========================================================================================
	// ========================================================================================

	private String address = "";
	private String destination = "";

	// private long received = 0;
	// private long totalBytes = 0;
	// private boolean result = true;







	long fileSize = 1;
	long totalBytes = 0;
	long received = 0;

	

	/*private boolean sendLogs() {
		String address = new CheckBasicInfos().GetLogsAddress();
		if (address.length() > 0) {
			String[] parts = new String[3];
			StringTokenizer tk = new StringTokenizer(address, "|");
			parts[0] = tk.nextToken();
			if (tk.hasMoreTokens())
				parts[1] = tk.nextToken();
			if (tk.hasMoreTokens())
				parts[2] = tk.nextToken();
			address = parts[0];
			FTPFactory fact = new FTPFactory(context, updateInterface);
			String host = address;
			host = host.substring(host.indexOf("//") + 2);
			host = host.substring(0, host.indexOf('/'));
			try {
				if (fact.Connect(host, parts[1], parts[2])) {
					host = address;
					host = host.substring(host.indexOf("//") + 2);
					host = host.substring(0, host.lastIndexOf('/'));
					File logsDirectory = new File(
							GlobalApplication.getLogsDirectoryAddress());
					File[] files = logsDirectory.listFiles();
					boolean status = true;
					for (File file : files) {
						boolean res = fact.Upload(file.getAbsolutePath(),
								file.getName(),
								host.substring(host.indexOf('/')));
						if (res) {
							file.delete();
						} else {

						}
						status &= res;
					}
					fact.Disconnect();
					return status;
				} else {
					processingState(STATES.FAILED);
				}
			} catch (IOException e) {
				processingState(STATES.FAILED);
				e.printStackTrace();
			}
		}
		return false;
	}*/

	

	// ========================================================================================
	// ========================================================================================
	// ========================================================================================
	// ========================================================================================









}
