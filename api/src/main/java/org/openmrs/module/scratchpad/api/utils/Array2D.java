package org.openmrs.module.scratchpad.api.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * dynamically adjustable 2 dimensional List that is accessed like an array.
 * @author rosssellars
 *
 * @param <X>
 * 
 * TODO rotate 90 (clockwise and anti-clock), mirror(horiz and vert), add multidimensions(maybe), remove row and column
 * 
 */	
public class Array2D <X> {

	private int rowSize = 0;
	private int columnSize = 0;
	private List<List<X>> array;
	
	public Array2D(){
		initialize(0,0);
	}
	
	public Array2D(int xSize, int ySize){
		initialize(xSize, ySize);
	}

	private void initialize(int xSize, int ySize){
		array = new ArrayList<List<X>>();
		makeSize(xSize,ySize);
		
	}
	/**
	 * Places an item in a cell specified, replacing the contents of that cell.  The matrix is dynamically expanded to accommodate out of bounds indices.
	 * @param x the row of the cell
	 * @param y the column of the cell
	 * @param value to be placed in the specified cell
	 */
	public void set(int x, int y, X value){
		if ( !((x<0)||(y<0)) ){
			/*
			if ((x>maxRow())||(y>maxColumn())){
				makeSize(x+1,y+1);
			}
			*/
			adjustArraySize(x, y);
			array.get(x).set(y, value);
		}
	}
	/**
	 * Returns the contents of a specified cell
	 * @param x the row of the cell required
	 * @param y the column of the cell required
	 * @return null if indexes out of bounds or empty cell
	 */
	public X get(int x, int y){
		if ((array.size()>x)&&(getSpecificColumnSize(x)>y)) {
			return array.get(x).get(y);
		} else {
			return null;
		}
		/*
		if ((x<sizeRows())&&(y<sizeColumns())){
			return array.get(x).get(y);
		}else{
			return null;
		}
		*/
	}
	/**
	 * The number of the rows in the matrix (ie one more than the max row index) 
	 * @return integer number of rows
	 */
	public int sizeRows(){
		return array.size();
	}
	/**
	 * The number of the columns in the matrix (ie one more than the max column index) 
	 * @return integer number of columns
	 */
	public int sizeColumns(){
		int retVar=0;
		if (sizeRows()>0) {
			for (List<X> item :array) {
				if (item.size()>retVar) {
					retVar = item.size();
				}
			}
		}
		return retVar;
	}
	private int getSpecificColumnSize(int rowNumber) {
		int retVar=0;
		if ( array.size()>rowNumber) {
			retVar = array.get(rowNumber).size();
		}
		return retVar;
	}
	/**
	 * The maximal row index (ie one less than the size)
	 * @return integer representing the largest index
	 */
	public int maxRow(){
		return sizeRows()-1;
	}
	/**
	 * The maximal column index (ie one less than the size)
	 * @return integer representing the largest index
	 */
	public int maxColumn(){
		return sizeColumns()-1;
	}
	/*
	 * adjusts the size of the matrix to new range.  It will expand rows and/or columns, but does not shrink
	 */
	/**
	 * @param rowNumber new  row to place data in
	 * @param columnNumber new colums to place data in
	 */

	private void adjustArraySize(int rowNumber, int columnNumber) {
		for (int loopVar=sizeRows(); loopVar<=rowNumber; loopVar++) {
			array.add(loopVar,new ArrayList<X>());
		}
		int thisColSize = array.get(rowNumber).size();
		for (int loopVar=thisColSize; loopVar<=columnNumber; loopVar++) {
			array.get(rowNumber).add(loopVar, null);
		}
	}
	@Deprecated
	private void makeSize(int newRowSize,int newColumnSize){
		for (int rowLoop = 0; rowLoop < newRowSize ; rowLoop++){
			if (!(rowLoop < array.size())){
				array.add(rowLoop,new ArrayList<X>());
			}
			for (int colLoop = 0; colLoop < newColumnSize; colLoop++){
				if (!(colLoop < array.get(rowLoop).size())){
					array.get(rowLoop).add(colLoop, null);
				}
			}
		}
		rowSize=array.size(); //newRowSize;
		columnSize=newColumnSize;
	}
	
	/**
	 * does pretty much what it says on the box
	 * rotates a 2D array to switch the X and Y axes
	 * @param inputList  to be flipped
	 * @return Array2D<>
	 */
	public Array2D<X> flipXandYaxis(){
		Array2D<X> retVar= new Array2D<X>();
		for (int loopA=0; loopA < this.sizeRows(); loopA++) {
			for (int loopB=0; loopB < this.sizeColumns();loopB++) {
				retVar.set(loopB,loopA, this.get(loopA,loopB));
			}
		}
		return retVar;
	}
	public List<List<X>> convertToList(){
		
		return array;
	}
	/**
	 * add a full row then adjust all columns to 
	 * @param newRowList
	 */
	public void addRow(List<X> newRowList){
		
		
		if (newRowList.size()>sizeColumns()){
			//adjust existing columns to new size
			makeSize(sizeRows(),newRowList.size());
		}else{
			//adjust new column to current size
			for (int colLoop = newRowList.size(); colLoop < sizeColumns(); colLoop++){
				newRowList.add(colLoop,null);
			}
		}
		array.add(newRowList);
		rowSize=rowSize+1;
		
	}
	
}
