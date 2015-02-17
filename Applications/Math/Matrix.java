/**
 * MATRIX
 * contains representation of matrix and basic functions:
 * - inverse (not implemented)
 * - transpose (not implemented) 
 * - determinant (faulty)
 * - reduce (not implemented)
 * - row reduce (not implemented) 
 */

public class Matrix {
	private double[][] data;
	private int size;
	
	public Matrix(int size) {
		data = new double[size][size];
	}
	
	public Matrix(double[][] starter) {
		data = starter;
		size = data.length;
	}
	
	public void set(int row, int col, double val) {
		data[row][col] = val;
	}
	
	private double get(int row, int col) {
		return data[row][col];
	}
	
	private double[] row(int row) {
		return data[row];
	}
	
	private double[] col(int col) {
		double[] result = new double[data.length];
		
		for (int i =0; i<col; i++) {
			result[i] = get(i, col);
		}
		
		return result;
	}

	/**
	 * Swaps two items in the matrix.*
	 * @param i: row 1
	 * @param j: col 1
	 * @param i2: row 2
	 * @param j2: col 2
	 */
	private void swap(int i, int j, int i2, int j2) {
		double v1 = get(i, j);
		double v2 = get(i2, j2);
		set(i2, j2, v1);
		set(i, j, v2);
	}

	/**
	 * Transponses matrix destructively.
	 */
	public void transpose() {
		for (int i = 0; i<data.length; i++) {
			for (int j = 0; j<row(i).length; j++) {
				swap(i, j, j, i);
			}
		}
	}

	/**
	 * Returns determinant of the matrix.*
	 * @return determinant
	 */
	public double determinant() {
		if (data.length == 2) {
			if (data[0].length != 2) {
				return 0;
			}
			double a = get(0, 0);
			double b = get(0, 1);
			double c = get(1, 0);
			double d = get(1, 1);
			return (a*d)-(b*c);
		}
		
		double sum = 0;
		
		boolean isNeg = false;
		
		for (int i = 0; i<data.length; i++) {
			for (int j = 0; j<row(i).length; j++) {
				double coeff = get(i, j);
				double term = coeff*allexcept(i, j).determinant();
				sum += (isNeg) ? -term : term;
				isNeg = !isNeg;
			}
		}
		
		return sum;
	}

	/**
	 * Returns matrix, excluding specified row and
	 * column.
	 * @param row
	 * @param col
	 * @return Matrix
	 */
	public Matrix allexcept(int row, int col) {
		
		Matrix nueue = new Matrix(data.length-1);

		int a = 0;
		int b;
		for (int i=0; i<data.length; i++) {
			if (i != row) {
				b = 0;
				for (int j = 0; j<row(i).length; j++) {
					if (j != col) {
						nueue.set(a, b, get(i, j));
						b += 1;
					}
				}
				a += 1;
			}
		}
		
		return nueue;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder("\n[");
		for (int i = 0; i < data.length; i++) {
			s.append("[");
			for (int j = 0; j < row(i).length; j++) {
				s.append(get(i, j)+" ");
			}
			s.append("]\n ");
		}
		return s.append("]").toString();
	}
		
	public static void main(String args[]) {
		Matrix m = new Matrix(2);
		m.set(0, 0, 1);
		m.set(1, 0, 2);
		m.set(0, 1, 3);
		m.set(1, 1, 4);
		System.out.println("matrix: " + m.toString());
		System.out.println("determinant: "+m.determinant());

		Matrix m2 = new Matrix(3);
		m2.set(0, 0, 1);
		m2.set(0, 1, 2);
		m2.set(0, 2, 3);
		m2.set(1, 0, 0);
		m2.set(1, 1, 5);
		m2.set(1, 2, 6);
		m2.set(2, 0, 0);
		m2.set(2, 1, 0);
		m2.set(2, 2, 9);
		System.out.println("matrix: " + m2.toString());
		System.out.println("determinant: "+m2.determinant());

		Matrix m3 = new Matrix(2);
		m3.set(0, 0, 1);
		m3.set(1, 0, 0);
		m3.set(0, 1, 3);
		m3.set(1, 1, 4);
		System.out.println("matrix: " + m3.toString());
		System.out.println("determinant: "+m3.determinant());
	}
}