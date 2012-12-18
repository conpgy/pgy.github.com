public abstract class MyAbstractList implements MyList
{
	protected int size = 0;	// The size of the list

	/** Created a default list */
	protected MyAbstractList()
	{
	}

	/** Created a list from an array of objects */
	protected MyAbstractList(Object[] objects)
	{
		for(int i = 0; i < objects.length; i++)
			this.add(objects[i]);
	}

	/** Add a new element o at the end of this list */
	public void add(Object o)
	{
		add(size, o);
	}

	/** Return true if this list contains no elements */
	public boolean isEmpty()
	{
		return size == 0;
	}

	/** Return the number of elements in this list */
	public int size()
	{
		return size;
	}

	/** Remove the first occurence of the element o from this list.
	 *  Shift any subsequent elements to the left.
	 *  Return true if the leement is removed */
	public boolean remove(Object o)
	{
		if(indexOf(o) >= 0)
		{
			remove(indexOf(o));
			return true;
		}
		else
			return false;
	}
}
