package com.reserv.myapplicationeli.views.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;


public class MainMenuDialog extends Dialog implements OnClickListener {
	Activity context;

	public MainMenuDialog(Activity context) {
		super(context);
		this.context = context;}
		/*requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menu);
		getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		Utility.applyFonts(findViewById(R.id.alert));
		findViewById(R.id.manageRequests).setOnClickListener(this);
		findViewById(R.id.update).setOnClickListener(this);
		findViewById(R.id.addRequest).setOnClickListener(this);
		findViewById(R.id.map).setOnClickListener(this);
		findViewById(R.id.cartable).setOnClickListener(this);
		findViewById(R.id.reports).setOnClickListener(this);
		findViewById(R.id.customers).setOnClickListener(this);
		findViewById(R.id.messaging).setOnClickListener(this);
		findViewById(R.id.help).setOnClickListener(this);
		findViewById(R.id.security).setOnClickListener(this);
		findViewById(R.id.profile).setOnClickListener(this);
		findViewById(R.id.deactivate).setOnClickListener(this);
		((TextView) findViewById(R.id.lastUpdateTime)).setText(new Config_Table().getValue(Config_Table.LAST_UPDATE));
		((TextView) findViewById(R.id.version)).setText(String.format("%s",Utility.getVersionInfos()));
		int messageCount = new Messages_Table().getUnreadMessageCount();
		if (messageCount > 0) {
			findViewById(R.id.messageCountPanel).setVisibility(View.VISIBLE);
			((TextView) findViewById(R.id.messageCount)).setText(messageCount
					+ "");
		} else
			findViewById(R.id.messageCountPanel).setVisibility(View.GONE);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.manageRequests:
			GlobalApplication.clearStack();
			System.out.println("11111AddRequestActivity.SELECTED_POSITION:  "+AddRequestActivity.SELECTED_POSITION);
			AddRequestActivity.SELECTED_POSITION="-1";
			AddRequestActivity.SELECTED_DETAIL="1";
			AddRequestActivity.SELECTED_CONDITIONID="-1";
			System.out.println("22222AddRequestActivity.SELECTED_POSITION:  "+AddRequestActivity.SELECTED_POSITION);
			Intent intent10 = new Intent(getContext(),ManageRequestsActivity.class);
			getContext().startActivity(intent10);
			break;
		case R.id.update:
			refreshData();
			break;
		case R.id.addRequest:
			GlobalApplication.clearStack();
			//System.out.println("11111AddRequestActivity.SELECTED_POSITION:  "+AddRequestActivity.SELECTED_POSITION);
			AddRequestActivity.SELECTED_POSITION="-1";
			AddRequestActivity.SELECTED_DETAIL="1";
			AddRequestActivity.SELECTED_CONDITIONID="-1";
			//System.out.println("22222AddRequestActivity.SELECTED_POSITION:  "+AddRequestActivity.SELECTED_POSITION);
			Intent intent = new Intent(getContext(),SelectCustomersActivity.class);
			intent.putExtra("TYPE", SelectCustomersActivity.TYPE_ADD_REQUEST);
			getContext().startActivity(intent);
			
			break;
		case R.id.map:
			GlobalApplication.clearStack();
			getContext().startActivity(new Intent(getContext(), MapActivity.class));
			break;
		case R.id.cartable:
			GlobalApplication.clearStack();
			getContext().startActivity(
					new Intent(getContext(), CartableActivity.class));
			break;
		case R.id.messaging:
			GlobalApplication.clearStack();
			getContext().startActivity(new Intent(getContext(), MessagesActivity.class));
			break;
		case R.id.reports:
			ReportsDialog dialog = new ReportsDialog(context);
			dialog.show();
			break;
		case R.id.customers:
			GlobalApplication.clearStack();
			Intent intent1 = new Intent(getContext(),
					SelectCustomersActivity.class);
			intent1.putExtra("TYPE",SelectCustomersActivity.TYPE_CUSTOMER_INFOS);
			getContext().startActivity(intent1);
			break;
		case R.id.deactivate:
			IDM_Dialog daDialog = new IDM_Dialog(
					GlobalApplication.getActivity());
			daDialog.setMessage("Ø¢ÛŒØ§ Ù…Ø§ÛŒÙ„ Ø¨Ù‡ ØºÛŒØ± Ù�Ø¹Ø§Ù„ Ø³Ø§Ø²ÛŒ Ù†Ø±Ù… Ø§Ù�Ø²Ø§Ø± Ù‡Ø³ØªÛŒØ¯ØŸ");
			daDialog.setCancelButton();
			daDialog.setAcceptButton(new OnAcceptInterface() {
				@Override
				public void accept() {
					Intent intent2 = new Intent(getContext(),SplashActivity.class);
					intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_CLEAR_TASK);
					Config_Table configs = new Config_Table();
					configs.updateData(Config_Table.IS_ACTIVE, "false");
					getContext().startActivity(intent2);
				}
			});
			daDialog.show();
			break;
		case R.id.profile:
			getContext().startActivity(new Intent(getContext(), ProfileActivity.class));
			break;
		case R.id.security:
			new SecurityDialog(getContext()).show();
			break;
			////
		case R.id.help:
			GlobalApplication.clearStack();
			Intent intent3 = new Intent(getContext(),SelectCustomersActivity.class);
			intent3.putExtra("TYPE", SelectCustomersActivity.TYPE_ADD_REQUEST);
			getContext().startActivity(intent3);
			break;
		}
		dismiss();
	}

	private void refreshData() {
		UpdateProgressDialog update = new UpdateProgressDialog(context) {
			@Override
			public void updateFinishedSuccessfully(int methodType) {
				try {
					GlobalApplication.getActivity().updateBasicInfosFinished();
				} catch (Exception e) {
				}
			}
		};
		update.show();
		update.StartUpdate(UpdateFromWebService.GET_FULL_UPDATE);
	}*/

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
