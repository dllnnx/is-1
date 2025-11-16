import { ReactNode } from 'react';

interface FormSectionProps {
  title: string;
  children: ReactNode;
  columns?: 1 | 2 | 3;
}

export const FormSection = ({
  title,
  children,
  columns = 1,
}: FormSectionProps) => {
  const gridClass =
    columns === 1
      ? ''
      : columns === 2
        ? 'grid grid-cols-2 gap-4'
        : 'grid grid-cols-3 gap-4';

  return (
    <div className="border rounded-lg p-4">
      <h2 className="text-lg font-semibold mb-3">{title}</h2>
      <div className={gridClass}>{children}</div>
    </div>
  );
};
