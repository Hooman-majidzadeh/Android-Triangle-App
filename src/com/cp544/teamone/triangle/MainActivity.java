package com.cp544.teamone.triangle;

import java.util.Locale;

import android.R.bool;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Point;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements ActionBar.TabListener {

	public UnitTestGameGenerateTriangle unitTest;
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(mSectionsPagerAdapter.getListener(i)));
		}

		TextView displayName;
		ImageView displayImage;
		displayName = (TextView) findViewById(R.id.textView6);
		displayImage = (ImageView) findViewById(R.id.display);
		
		unitTest = new UnitTestGameGenerateTriangle(displayName, displayImage);
		unitTest.testIsoselese();
		unitTest.testEquilateral();
		unitTest.testScalene();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public class Game {
		int bottomLength, leftLength, rightLength;
		Button btnGenerate, btnClear;
		EditText inputBottom, inputLeft, inputRight;
		TextView disaplyTriangleName, labelResults;
		ImageView displayTriangleImage;
		
		public void invalidInput(TextView tView, ImageView iView) {
			tView.setText("Error:Please try again, remember your number must be less than 100");
			iView.setImageResource(android.R.color.transparent);
			//Log.d("Triangle Generate", "Clear fields. Sucess!");
		}
		
		public void viewIsosceles(TextView tView, ImageView iView){
			tView.setText("Isosceles Triangle: Two Congruent Sides");
			iView.setImageResource(R.drawable.i_triangle);	
			//Log.d("Triangle Generate", "View Isoseles. Sucess!");
		}		
		
		public void viewEquilateral(TextView tView, ImageView iView){	
			tView.setText("Equilateral Triangle: All sides are equal");
			iView.setImageResource(R.drawable.e_triangle);
			//Log.d("Triangle Generate", "View Equilateral. Sucess!");
		}	
		
		public void viewScalene(TextView tView, ImageView iView){
			tView.setText("Scalene Triangle: No Congruent Sides");
			iView.setImageResource(R.drawable.s_triangle);
			//Log.d("Triangle Generate", "View Scalene. Sucess!");
		}
		
		public void clearFields(View v){
			if (v.getId() == R.id.button2) {
				inputBottom.setText("");
				inputLeft.setText("");
				inputRight.setText("");
				disaplyTriangleName.setText("");
				displayTriangleImage.setImageResource(android.R.color.transparent);
			}
		}
		
		public int gameGenerateTriangle(TextView tView, ImageView iView, int a, int b, int c) {				
			if (((a == b && b != c) || (a == c && b != c) || (b == c && a != c))
					&& (a <= 100 && b <= 100 && c <= 100)) {
				viewIsosceles(tView, iView);
				return 2;
			} else if ((a == b && a == c)
					&& (a <= 100 && b <= 100 && c <= 100)) {
				viewEquilateral(tView, iView);
				return 1;
			} else if ((a != b && a != c && b != c)
					&& (a <= 100 && b <= 100 && c <= 100)) {
				viewScalene(tView, iView);
				return 3;
			} else {
				invalidInput(tView, iView);
				return 0;
			}
		}
		
		public void gameLogic(TextView tView, ImageView iView, EditText eText1, EditText eText2, EditText eText3){				
			// convert text to int
			bottomLength = Integer.parseInt(eText1.getText().toString());
			leftLength = Integer.parseInt(eText2.getText().toString());
			rightLength = Integer.parseInt(eText3.getText().toString());

			// Choose triangle. 
			gameGenerateTriangle(tView, iView, bottomLength, leftLength, rightLength);			
		}
		
		public void gameLayout(){
			setContentView(R.layout.game);
			// assigning the values
			inputBottom = (EditText) findViewById(R.id.editText1);
			inputLeft = (EditText) findViewById(R.id.editText2);
			inputRight = (EditText) findViewById(R.id.editText3);
			disaplyTriangleName = (TextView) findViewById(R.id.textView6);
			labelResults = (TextView) findViewById(R.id.textView5);
			btnGenerate = (Button) findViewById(R.id.button1);
			btnClear = (Button) findViewById(R.id.button2);
			displayTriangleImage = (ImageView) findViewById(R.id.display);

			// setting up Gen button
			btnGenerate.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {								 
					gameLogic(disaplyTriangleName, displayTriangleImage, 
							inputBottom, inputLeft, inputRight);
				}
			});

			// setting up the clear button
			btnClear.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					clearFields(v);
				}
			});
		}
	}
	
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}
		
		
		public void selectTabLogic(ActionBar.Tab tab, FragmentTransaction ft){			
			// show the given tab
			if (tab.getPosition() == 0) {
				Game game = new Game(); 
				game.gameLayout();
			}
			if (tab.getPosition() == 1) {
				setContentView(R.layout.guide);
			}
			if (tab.getPosition() == 2) {
				setContentView(R.layout.about);
			}
		}
		
		public ActionBar.TabListener getListener(int position) {
			return new ActionBar.TabListener() {
				public void onTabSelected(ActionBar.Tab tab,
						FragmentTransaction ft) {	
					selectTabLogic(tab, ft);
				}

				public void onTabUnselected(ActionBar.Tab tab,
						FragmentTransaction ft) {
					// hide the given tab
				}

				public void onTabReselected(ActionBar.Tab tab,
						FragmentTransaction ft) {
					// probably ignore this event
				}
			};
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	public class UnitTestGameGenerateTriangle {
		// Outside Bounds returns 0
		// Isoselese returns 1
		// Equilateral returns 2
		// Scalene returns 3
		Game game = new Game();
		TextView tView;
		ImageView iView;
		// Bounds
		Point upperLower = new Point(0, 100);
		Point belowAbove = new Point(-1, 101);
		int center = 50;
		
		public UnitTestGameGenerateTriangle(TextView tv, ImageView iv){
			this.tView = tv;
			this.iView = iv;
		}
		
		public void testIsoselese(){
			Log.d("(TI) Isoseles Boundry Test", "Begin");			
			TIupper();
			TIlower();
			TIbelow();
			TIabove();
			TImiddle();
			Log.d("(TI) Isoseles Boundry Test", "End");
		}
		public void testEquilateral(){
			Log.d("(TE) Equilateral Boundry Test", "Begin");
			TEupper();
			TElower();
			TEbelow();
			TEabove();
			TEmiddle();
			Log.d("(TE) Equilateral Boundry Test", "End");
		}
		public void testScalene(){
			Log.d("(TS) Scalene Boundry Test", "Begin");
			TSupper();
			TSlower();
			TSbelow();
			TSabove();
			TSmiddle();
			Log.d("(TS) Scalene Boundry Test", "End");
		}
		int value;
		// (TI) Test Isoselese
		public void TIupper(){
			value = game.gameGenerateTriangle(tView, iView, upperLower.y, upperLower.y, upperLower.y);
			if(value == 1){
				Log.d("TI Upper Bounds", "Passed!");
			} else {				
				Log.e("TI Upper Bounds", "Did not pass.");
			}
		}
		public void TIlower(){
			value = game.gameGenerateTriangle(tView, iView, upperLower.x, upperLower.x, upperLower.x);
			if(value == 1){
				Log.d("TI Lower Bounds", "Passed!");
			} else {				
				Log.e("TI Lower Bounds", "Did not pass.");
			}			
		}
		public void TIbelow(){
			value = game.gameGenerateTriangle(tView, iView, belowAbove.x, belowAbove.x, belowAbove.x);
			if(value == 0){
				Log.d("TI Below Bounds", "Passed!");
			} else {				
				Log.e("TI Below Bounds", "Did not pass.");
			}			
		}
		public void TIabove(){
			value = game.gameGenerateTriangle(tView, iView, belowAbove.y, belowAbove.y, belowAbove.y);
			if(value == 0){
				Log.d("TI Above Bounds", "Passed!");
			} else {				
				Log.e("TI Above Bounds", "Did not pass.");
			}			
		}
		public void TImiddle(){
			value = game.gameGenerateTriangle(tView, iView, center, center, center);
			if(value == 1){
				Log.d("TI In Bounds", "Passed!");
			} else {				
				Log.e("TI In Bounds", "Did not pass.");
			}			
		}
		
		// (TE) Test Equilateral
		public void TEupper(){
			if(game.gameGenerateTriangle(tView, iView, upperLower.y, upperLower.y, center) == 2){
				Log.d("TE Upper Bounds", "Passed!");
			} else {				
				Log.e("TE Upper Bounds", "Did not pass.");
			}
		}
		public void TElower(){
			if(game.gameGenerateTriangle(tView, iView, upperLower.x, upperLower.x, center) == 2){
				Log.d("TE Lower Bounds", "Passed!");
			} else {				
				Log.e("TE Lower Bounds", "Did not pass.");
			}			
		}
		public void TEbelow(){
			if(game.gameGenerateTriangle(tView, iView, belowAbove.x, belowAbove.x, center) == 0){
				Log.d("TE Below Bounds", "Passed!");
			} else {				
				Log.e("TE Below Bounds", "Did not pass.");
			}			
		}
		public void TEabove(){
			if(game.gameGenerateTriangle(tView, iView, belowAbove.y, belowAbove.y, center) == 0){
				Log.d("TE Above Bounds", "Passed!");
			} else {				
				Log.e("TE Above Bounds", "Did not pass.");
			}			
		}
		public void TEmiddle(){
			if(game.gameGenerateTriangle(tView, iView, center + 25, center, center) == 2){
				Log.d("TE In Bounds", "Passed!");
			} else {				
				Log.e("TE In Bounds", "Did not pass.");
			}			
		}

		// (TS) Test Scalene
		public void TSupper(){
			if(game.gameGenerateTriangle(tView, iView, upperLower.y, center + 25, center) == 3){
				Log.d("TS Upper Bounds", "Passed!");
			} else {				
				Log.e("TS Upper Bounds", "Did not pass.");
			}
		}
		public void TSlower(){
			if(game.gameGenerateTriangle(tView, iView, upperLower.x, center + 25, center) == 3){
				Log.d("TS Lower Bounds", "Passed!");
			} else {				
				Log.e("TS Lower Bounds", "Did not pass.");
			}			
		}
		public void TSbelow(){
			if(game.gameGenerateTriangle(tView, iView, belowAbove.x, center + 25, center) == 0){
				Log.d("TS Below Bounds", "Passed!");
			} else {				
				Log.e("TS Below Bounds", "Did not pass.");
			}			
		}
		public void TSabove(){
			if(game.gameGenerateTriangle(tView, iView, belowAbove.y, center + 25, center) == 0){
				Log.d("TS Above Bounds", "Passed!");
			} else {				
				Log.e("TS Above Bounds", "Did not pass.");
			}			
		}
		public void TSmiddle(){
			if(game.gameGenerateTriangle(tView, iView, center + 25, center - 25, center) == 3){
				Log.d("TS In Bounds", "Passed!");
			} else {				
				Log.e("TS In Bounds", "Did not pass.");
			}			
		}
	}

}
