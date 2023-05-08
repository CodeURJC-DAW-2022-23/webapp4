import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderLoginComponent } from './header.component';

describe('HeaderLoginComponent', () => {
  let component: HeaderLoginComponent;
  let fixture: ComponentFixture<HeaderLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeaderLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
